import { useEffect, useState } from 'react';
import axios from 'axios';

type Props = { id:number; onBack:()=>void };
type Chamado = { id:number; titulo:string; descricao:string; status:string; prioridade:string; diagnostico?:string; solucao?:string; criadoEm?:string; atualizadoEm?:string };
type Diagnostico = { id:number; etapa:string; resultado:string; observacao?:string; criadoEm:string };
const api=axios.create({baseURL:'http://localhost:8080/api'});
const statuses=['ABERTO','EM_ANALISE','EM_ATENDIMENTO','AGUARDANDO_USUARIO','RESOLVIDO','ENCERRADO'];
export default function ChamadoDetalhe({id,onBack}:Props){
 const [chamado,setChamado]=useState<Chamado|null>(null); const [diag,setDiag]=useState<Diagnostico[]>([]); const [etapa,setEtapa]=useState('Conectividade'); const [resultado,setResultado]=useState(''); const [observacao,setObservacao]=useState(''); const [erro,setErro]=useState('');
 const carregar=()=>{Promise.all([api.get(`/chamados/${id}`),api.get(`/chamados/${id}/diagnosticos`)]).then(([c,d])=>{setChamado(c.data);setDiag(d.data)}).catch(()=>setErro('Não foi possível carregar o chamado.'))};
 useEffect(carregar,[id]);
 async function status(s:string){try{await api.patch(`/chamados/${id}/status?status=${s}`);carregar()}catch{setErro('Não foi possível atualizar o status.')}}
 async function adicionar(){if(!resultado.trim())return;try{await api.post(`/chamados/${id}/diagnosticos`,{etapa,resultado,observacao});setResultado('');setObservacao('');carregar()}catch{setErro('Não foi possível registrar o diagnóstico.')}}
 if(!chamado)return <main className="container"><button className="secondary" onClick={onBack}>← Voltar</button><p>{erro||'Carregando...'}</p></main>;
 return <main className="container"><button className="secondary" onClick={onBack}>← Voltar para chamados</button><div className="form-header" style={{marginTop:24}}><div><span className="eyebrow">CHAMADO #{chamado.id}</span><h1>{chamado.titulo}</h1><p>{chamado.descricao}</p></div><span className="badge">{chamado.prioridade}</span></div>{erro&&<div className="error">{erro}</div>}
 <div className="detail-grid"><section className="detail-card"><h2>Fluxo do atendimento</h2><p>Atualize o status conforme a evolução do incidente.</p><div style={{display:'flex',gap:8,flexWrap:'wrap',margin:'18px 0'}}>{statuses.map(s=><button key={s} className={s===chamado.status?'primary':'secondary'} onClick={()=>status(s)}>{s.replaceAll('_',' ')}</button>)}</div><h2 style={{marginTop:28}}>Diagnóstico / troubleshooting</h2><label>Etapa<select value={etapa} onChange={e=>setEtapa(e.target.value)}><option>Conectividade</option><option>Hardware</option><option>Software</option><option>Acesso</option><option>Impressão</option><option>Configuração</option></select></label><label>Resultado<textarea rows={4} value={resultado} onChange={e=>setResultado(e.target.value)} placeholder="Ex.: DNS respondendo, porém gateway indisponível." /></label><label>Observação<textarea rows={3} value={observacao} onChange={e=>setObservacao(e.target.value)} /></label><button className="primary" onClick={adicionar}>Registrar diagnóstico</button></section>
 <aside className="detail-card"><h2>Histórico</h2><div className="timeline">{diag.length===0?<p>Nenhum diagnóstico registrado.</p>:diag.map(d=><div className="timeline-item" key={d.id}><strong>{d.etapa}</strong><span className="muted">{new Date(d.criadoEm).toLocaleString('pt-BR')}</span><p>{d.resultado}</p>{d.observacao&&<span className="muted">{d.observacao}</span>}</div>)}</div></aside></div></main>;
}
