package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abctreinamentos.Cliente;
import com.abctreinamentos.Curso;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAApp");
	EntityManager em = emf.createEntityManager();
	
	
	
	public Controlador() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int idformulario;
		int tipoformulario;
		
		String cpfmascara , nome, email;
		
		long cpf , cdcurso , valor;
		String nomeCurso, valorCurso, url, datainscricao;
		
		
		idformulario    = Integer.parseInt(request.getParameter("idformulario"));
		tipoformulario  = Integer.parseInt(request.getParameter("tipoformulario"));
		EntityTransaction tx = em.getTransaction();
		HttpSession session = request.getSession();
		
		
		
		
		if(idformulario == 1) {
		switch(tipoformulario)
			{	
			case 11: //Consultar Todos
			{/*
				System.out.println("[1] Consultar Todos");
				TypedQuery<Cliente> query = em.createQuery(""
						+ "Select c from Cliente c",Cliente.class);
				List<Cliente> clientes = query.getResultList();
				clientes.forEach(System.out::println);
				break;*/
			}
			case 12: //Consultar
			{
				
				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				Cliente cliente = em.find(Cliente.class,cpf);
				
				if(cliente != null) 
				{
					session.setAttribute("mensagem", "Cliente: "+cpf+" Encontrado");
					session.setAttribute("cliente", cliente);
				}
				else
				{
					session.setAttribute("mensagem", "Cliente: "+cpf+" Não Encontrado");
					session.setAttribute("cliente", null);
				}
				response.sendRedirect("clientes/resultado.jsp");
				break;						
			}
			case 13: //Cadastrar 
			{
				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				nome = request.getParameter("nome");
				email = request.getParameter("email");
				
				Cliente cliente = new Cliente(cpf,nome,email);
				tx.begin();
				em.persist(cliente);
				tx.commit();	
				session.setAttribute("mensagem", "Cliente: "+cpf+" Cadastrado!");
				session.setAttribute("cliente", cliente);
				response.sendRedirect("clientes/resultado.jsp");
				break;					
			}
			case 14: //Alterar
			{
				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]","");
				cpf = Long.parseLong(cpfmascara);
				nome = request.getParameter("nome");
				email = request.getParameter("email");
				
				Cliente cliente = em.find(Cliente.class,cpf);
				
				if(cliente != null) //cliente encontrado
				{
					cliente = new Cliente(cpf,nome,email);
					tx.begin();
					em.merge(cliente);
					tx.commit();
					
					session.setAttribute("mensagem", "Cliente "+cpf+" Alterado!");
					session.setAttribute("cliente",cliente);
				}
				else //cliente não existe
				{
					session.setAttribute("mensagem", "Cliente "+cpf+" Não Encontrado. Alteração Cancelada!");
					session.setAttribute("cliente",null);					
				}
				response.sendRedirect("clientes/resultado.jsp");				
				break;
			}
			case 15: //Excluir
			{

				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				Cliente cliente = em.find(Cliente.class,cpf);
				
				if(cliente != null) 
				{
					tx.begin();
					em.remove(cliente);
					tx.commit();
					session.setAttribute("mensagem", "Cliente: "+cpf+" Excluido!");
					
				}
				else
					session.setAttribute("mensagem", "Cliente: "+cpf+" Não Encontrado Exclusão cancelada!");
					session.setAttribute("cliente", null);
					response.sendRedirect("clientes/resultado.jsp");
				break;						
			 }
			
			
		   }
		}
		else if(idformulario ==2) {
			
			switch(tipoformulario)
			{
				case 21: //Consultar Todos
				{
					/*
				}
					System.out.println("[1] Consultar Todos");
					TypedQuery<Curso> query = em.createQuery(""
							+ "Select c from Curso c",Curso.class);
					List<Curso> cursos = query.getResultList();
					cursos.forEach(System.out::println);
					break;*/
				}
				case 22: //Consultar
				{



					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					Curso curso = em.find(Curso.class,cdcurso);
					if(curso != null) 
					{
						session.setAttribute("mensagem", "Curso: "+cdcurso+" Encontrado");
						session.setAttribute("cliente", curso);
					}
					else
					{
						session.setAttribute("mensagem", "Curso: "+cdcurso+" Não Encontrado");
						session.setAttribute("curso", null);
					}
					response.sendRedirect("cursos/resultado.jsp");
					break;						
				}
				case 23: //Cadastrar 
				{
					
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					nomeCurso = request.getParameter("nome");
					valorCurso = request.getParameter("valor");
					url   = request.getParameter("site");
					valor = Long.parseLong(valorCurso);
					
					Curso curso = new Curso(cdcurso,nomeCurso,valor,url);
					tx.begin();
					em.persist(curso);
					tx.commit();	
					session.setAttribute("mensagem", "Curso: "+cdcurso+" Cadastrado!");
					session.setAttribute("curso", curso);
					response.sendRedirect("cursos/resultado.jsp");
					break;					
				}
				case 24: //Alterar
				{
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					nomeCurso = request.getParameter("nome");
					valorCurso     = request.getParameter("valor");
					url       = request.getParameter("site");
					valor = Long.parseLong(valorCurso);
					
					Curso curso = em.find(Curso.class,cdcurso);
					
					if(curso != null) 
					{
						curso = new Curso(cdcurso,nomeCurso,valor,url);
						tx.begin();
						em.merge(curso);
						tx.commit();
						
						session.setAttribute("mensagem", "Curso "+cdcurso+" Alterado!");
						session.setAttribute("curso",curso);
					}
					else 
					{
						session.setAttribute("mensagem", "Curso "+cdcurso+" Não Encontrado. Alteração Cancelada!");
						session.setAttribute("curso",null);					
					}
					response.sendRedirect("cursos/resultado.jsp");
					break;
				}
				case 25: //Excluir
				{
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					Curso curso = em.find(Curso.class,cdcurso);
					if(curso != null) 
					{
						tx.begin();
						em.remove(curso);
						tx.commit();
						session.setAttribute("mensagem", "Curso: "+cdcurso+" Excluido!");
						
					}
					else
						session.setAttribute("mensagem", "Curso: "+cdcurso+" Não Encontrado Exclusão cancelada!");
						session.setAttribute("curso", null);
						response.sendRedirect("cursos/resultado.jsp");
					break;						
				}
			
			
		}
	}
	 else if(idformulario ==3) {
			
		 switch(tipoformulario)
			{
				case 31: //Consultar Todos
				{
					/*
					System.out.println("[1] Consultar Todos");
					TypedQuery<Pagamento> query = em.createQuery(""
							+ "Select p from Pagamento p",Pagamento.class);
					List<Pagamento> pagamentos = query.getResultList();
					pagamentos.forEach(System.out::println);
					break;*/
				}
				case 32: //Consultar
				{
					
					cpfmascara = request.getParameter("cpf");
					cpfmascara = cpfmascara.replaceAll("[.-]", "");
					cpf = Long.parseLong(cpfmascara);
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					out.println("<h2> Consultar => Pagamento => </h2>"+cdcurso+ " - "+cpf);
					
					/*
					PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
					Pagamento pagamento = em.find(Pagamento.class,pgtoid);
					System.out.println(pagamento);*/
					break;						
				}
				case 33: //Cadastrar 
				{
					
					cpfmascara = request.getParameter("cpf");
					cpfmascara = cpfmascara.replaceAll("[.-]", "");
					cpf = Long.parseLong(cpfmascara);
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					
					//PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
					
					datainscricao = request.getParameter("datainscricao");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(datainscricao, formatter);
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					out.println("<h2> cadastrar => Pagamento => </h2>"+cpf+ " - "+cdcurso+ " - "+fmt.format(date));
					
					/*Pagamento pagamento = new Pagamento(pgtoid,datainscricao);
					tx.begin();
					em.persist(pagamento);
					tx.commit();*/
					break;					
				}
				case 34: //Alterar
				{
					cpfmascara = request.getParameter("cpf");
					cpfmascara = cpfmascara.replaceAll("[.-]", "");
					cpf = Long.parseLong(cpfmascara);
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					//PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
					datainscricao = request.getParameter("datainscricao");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(datainscricao, formatter);
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					out.println("<h2> Alterarr => Pagamento => </h2>"+cpf+ " - "+cdcurso+ " - "+fmt.format(date));
					/*Pagamento pagamento = new Pagamento(pgtoid,datainscricao);
					tx.begin();
					em.merge(pagamento);
					tx.commit();*/
					break;
				}
				case 35: //Excluir
				{
					cpfmascara = request.getParameter("cpf");
					cpfmascara = cpfmascara.replaceAll("[.-]", "");
					cpf = Long.parseLong(cpfmascara);
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					out.println("<h2> Excluir => Pagamento => </h2>"+cdcurso+ " - "+cpf);
					/*
					PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
					Pagamento pagamento = em.find(Pagamento.class,pgtoid);
					tx.begin();
					em.remove(pagamento);
					tx.commit();*/
					break;						
				}
			}
			
		}
	
	
	}

}
