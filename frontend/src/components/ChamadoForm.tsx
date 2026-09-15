import { FormEvent, useEffect, useState } from 'react';
import axios from 'axios';

type Props = { onCreated: () => void; onCancel: () => void };
type Option = { id: number; nome: string };

const api = axios.create({ baseURL: 'http://localhost:8080/api' });

export default function ChamadoForm({ onCreated, onCancel }: Props) {
  const [categorias, setCategorias] = useState<Option[]>([]);
  const [usuarios, setUsuarios] = useState<Option[]>([]);
  const [titulo, setTitulo] = useState('');
  const [descricao, setDescricao] = useState('');
  const [prioridade, setPrioridade] = useState('MEDIA');
  const [categoriaId, setCategoriaId] = useState('');
  const [usuarioId, setUsuarioId] = useState('');
  const [erro, setErro] = useState('');
  const [salvando, setSalvando] = useState(false);

  useEffect(() => {
    Promise.all([api.get('/categorias'), api.get('/usuarios')])
      .then(([c, u]) => { setCategorias(c.data); setUsuarios(u.data); })
      .catch(() => setErro('Não foi possível carregar categorias e usuários.'));
  }, []);

  async function submit(e: FormEvent) {
    e.preventDefault();
    setErro(''); setSalvando(true);
    try {
      await api.post('/chamados', { titulo, descricao, prioridade, categoriaId: Number(categoriaId), usuarioId: Number(usuarioId) });
      onCreated();
    } catch { setErro('Não foi possível abrir o chamado. Verifique os dados.'); }
    finally { setSalvando(false); }
  }

  return <form className="form-card" onSubmit={submit}>
    <div className="form-header"><div><span className="eyebrow">ATENDIMENTO</span><h2>Novo chamado</h2><p>Registre o incidente com informações suficientes para a triagem.</p></div><button type="button" className="secondary" onClick={onCancel}>Cancelar</button></div>
    {erro && <div className="error">{erro}</div>}
    <label>Título<input required value={titulo} onChange={e => setTitulo(e.target.value)} placeholder="Ex.: Notebook sem acesso à internet" /></label>
    <label>Descrição<textarea required value={descricao} onChange={e => setDescricao(e.target.value)} placeholder="Informe o que aconteceu, quando começou e qual impacto gerou." rows={5} /></label>
    <div className="form-grid">
      <label>Prioridade<select value={prioridade} onChange={e => setPrioridade(e.target.value)}><option>BAIXA</option><option>MEDIA</option><option>ALTA</option><option>CRITICA</option></select></label>
      <label>Categoria<select required value={categoriaId} onChange={e => setCategoriaId(e.target.value)}><option value="">Selecione</option>{categorias.map(c => <option key={c.id} value={c.id}>{c.nome}</option>)}</select></label>
      <label>Usuário solicitante<select required value={usuarioId} onChange={e => setUsuarioId(e.target.value)}><option value="">Selecione</option>{usuarios.map(u => <option key={u.id} value={u.id}>{u.nome}</option>)}</select></label>
    </div>
    <button className="primary" disabled={salvando}>{salvando ? 'Abrindo chamado...' : 'Abrir chamado'}</button>
  </form>;
}
