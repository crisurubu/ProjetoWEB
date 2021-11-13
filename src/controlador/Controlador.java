package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controlador() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int idformulario;
		int tipoformulario;
		
		idformulario    = Integer.parseInt(request.getParameter("idformulario"));
		tipoformulario  = Integer.parseInt(request.getParameter("tipoformulario"));
		
		String cpfmascara , nome, email;
		long cpf , cdcurso;
		String nomeCurso, valor, url, datainscricao;
		
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
				out.println("<h2>Cliente => consultar => </h2>"+cpf);
				//Cliente cliente = em.find(Cliente.class,cpf);
				
				break;						
			}
			case 13: //Cadastrar 
			{
				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				nome = request.getParameter("nome");
				email = request.getParameter("email");
				out.println("<h2>Cliente => Cadastrar => </h2>"+cpf+" - "+nome+" - "+email);
				
				/*
				Cliente cliente = new Cliente(cpf,nome,email);
				tx.begin();
				em.persist(cliente);
				tx.commit();	*/					
				break;					
			}
			case 14: //Alterar
			{
				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				nome = request.getParameter("nome");
				email = request.getParameter("email");
				out.println("<h2>Cliente => Alterar => </h2>"+cpf+" - "+nome+" - "+email);
				/*Cliente cliente = new Cliente(cpf,nome,email);
				tx.begin();
				em.merge(cliente);
				tx.commit();*/						
				break;
			}
			case 15: //Excluir
			{

				cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				out.println("<h2>Cliente => excluir => </h2>"+cpf);
				/*Cliente cliente = em.find(Cliente.class,cpf);
				tx.begin();
				em.remove(cliente);
				tx.commit();*/						
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
					out.println("<h2>Curso => consultar curso especifico => </h2>"+cdcurso);
					/*Curso curso = em.find(Curso.class,cdcurso);
					System.out.println(curso);*/
					break;						
				}
				case 23: //Cadastrar 
				{
					
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					nomeCurso = request.getParameter("nome");
					valor     = request.getParameter("valor");
					url       = request.getParameter("site");
					out.println("<h2>Curso => Cadastrar curso => </h2>"+cdcurso+ " - "+nomeCurso+" - "+valor+" - "+url);
					
					/*Curso curso = new Curso(cdcurso,nome,valor,url);
					tx.begin();
					em.persist(curso);
					tx.commit();*/
					break;					
				}
				case 24: //Alterar
				{
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					nomeCurso = request.getParameter("nome");
					valor     = request.getParameter("valor");
					url       = request.getParameter("site");
					out.println("<h2>Curso => Alterar curso => </h2>"+cdcurso+ " - "+nomeCurso+" - "+valor+" - "+url);
					/*
					Curso curso = new Curso(cdcurso,nome,valor,url);
					tx.begin();
					em.merge(curso);
					tx.commit();*/
					break;
				}
				case 25: //Excluir
				{
					cdcurso = Long.parseLong(request.getParameter("cdcurso"));
					out.println("<h2>Curso => excluir curso especifico => </h2>"+cdcurso);
					/*
					tx.begin();
					em.remove(curso);
					tx.commit();	//*/
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
