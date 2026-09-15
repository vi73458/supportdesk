import { useEffect, useState } from 'react';
import axios from 'axios';
import './styles.css';

type Chamado = { id:number; titulo:string; descricao:string; status:string; prioridade:string; categoria?:{nome:string} };
const api = axios.create({ baseURL:'http://localhost:8080/api' });

function App(){
 const [chamados,setChamados]=useState<Chamado[]>([]); const [erro,setErro]=useState('');
 useEffect(()=>{api.get<Chamado[]>('/chamados').then(r=>setChamados(r.data)).catch(()=>setErro('Não foi possível conectar à API. Inicie o backend na porta 8080.'));},[]);
 const abertos=chamados.filter(c=>!['RESOLVIDO','ENCERRADO'].includes(c.status)).length;
 const criticos=chamados.filter(c=>c.prioridade==='CRITICA').length;
 const resolvidos=chamados.filter(c=>['RESOLVIDO','ENCERRADO'].includes(c.status)).length;
 return <div className="app"><aside><div className="brand">Support<span>Desk</span></div><nav><a className="active">Dashboard</a><a>Chamados</a><a>Equipamentos</a><a>Base de conhecimento</a></nav><div className="profile">Victor Hugo<small>Analista de Suporte</small></div></aside><main><header><div><span className="eyebrow">IT SERVICE DESK</span><h1>Dashboard</h1><p>Visão geral da operação de suporte.</p></div><button>Novo chamado</button></header><section className="cards"><Card title="Chamados abertos" value={abertos}/><Card title="Críticos" value={criticos}/><Card title="Resolvidos" value={resolvidos}/><Card title="Total" value={chamados.length}/></section>{erro&&<div className="error">{erro}</div>}<section className="panel"><div className="panel-title"><h2>Chamados recentes</h2><span>{chamados.length} registros</span></div>{chamados.length===0?<p className="empty">Nenhum chamado cadastrado.</p>:<div className="table-wrap"><table><thead><tr><th>#</th><th>Chamado</th><th>Categoria</th><th>Prioridade</th><th>Status</th></tr></thead><tbody>{chamados.map(c=><tr key={c.id}><td>#{c.id}</td><td><b>{c.titulo}</b><small>{c.descricao}</small></td><td>{c.categoria?.nome??'—'}</td><td><span className="badge">{c.prioridade}</span></td><td><span className="badge">{c.status.replaceAll('_',' ')}</span></td></tr>)}</tbody></table></div>}</section></main></div>;
}
function Card({title,value}:{title:string,value:number}){return <div className="card"><span>{title}</span><strong>{value}</strong></div>}
export default App;
