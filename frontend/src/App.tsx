import { useEffect, useState } from 'react';
import axios from 'axios';

type Chamado = {
  id: number;
  titulo: string;
  descricao: string;
  status: string;
  prioridade: string;
};

const api = axios.create({ baseURL: 'http://localhost:8080/api' });

function App() {
  const [chamados, setChamados] = useState<Chamado[]>([]);
  const [erro, setErro] = useState('');

  useEffect(() => {
    api.get<Chamado[]>('/chamados')
      .then(response => setChamados(response.data))
      .catch(() => setErro('Não foi possível conectar à API. Inicie o backend na porta 8080.'));
  }, []);

  const total = chamados.length;
  const abertos = chamados.filter(c => c.status === 'ABERTO').length;
  const atendimento = chamados.filter(c => c.status === 'EM_ATENDIMENTO').length;
  const resolvidos = chamados.filter(c => c.status === 'RESOLVIDO').length;

  return (
    <main className="container">
      <header>
        <div>
          <span className="eyebrow">IT SERVICE DESK</span>
          <h1>SupportDesk</h1>
          <p>Central de suporte, incidentes e acompanhamento de SLA.</p>
        </div>
        <button>Novo chamado</button>
      </header>

      <section className="cards">
        <div><span>Total</span><strong>{total}</strong></div>
        <div><span>Abertos</span><strong>{abertos}</strong></div>
        <div><span>Em atendimento</span><strong>{atendimento}</strong></div>
        <div><span>Resolvidos</span><strong>{resolvidos}</strong></div>
      </section>

      {erro && <div className="error">{erro}</div>}

      <section className="panel">
        <div className="panel-title"><h2>Chamados recentes</h2><span>{total} chamados</span></div>
        {chamados.length === 0 ? <p className="empty">Nenhum chamado cadastrado.</p> : (
          <div className="table-wrap">
            <table>
              <thead><tr><th>#</th><th>Chamado</th><th>Prioridade</th><th>Status</th></tr></thead>
              <tbody>{chamados.map(c => (
                <tr key={c.id}><td>#{c.id}</td><td><b>{c.titulo}</b><small>{c.descricao}</small></td><td><span className="badge">{c.prioridade}</span></td><td>{c.status}</td></tr>
              ))}</tbody>
            </table>
          </div>
        )}
      </section>
    </main>
  );
}

export default App;
