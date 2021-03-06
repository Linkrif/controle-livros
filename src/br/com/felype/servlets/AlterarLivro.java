package br.com.felype.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.felype.modelos.Livro;
import br.com.felype.modelos.LivroDao;

/**
 * Servlet implementation class CadastrarLivro
 */
@WebServlet("/alterar")
public class AlterarLivro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		LivroDao dao = new LivroDao();
		try {
			// busca o writer
			// PrintWriter out = response.getWriter();
			// buscando os parāmetros no request
			int id = Integer.parseInt(request.getParameter("id"));
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			int paginas;
			try {
				paginas = Integer.parseInt(request.getParameter("paginas"));
			} catch (Exception e) {
				paginas = 0;
			}

			int paginaAtual;
			try {
				paginaAtual = Integer.parseInt(request.getParameter("pagina-atual"));
			} catch (Exception e) {
				paginaAtual = 0;
			}
			
			String urlImage;
			try {
				urlImage = request.getParameter("url-image");
			} catch (Exception e) {
				urlImage = "./img/flowers.jpg";
			}

			// monta um objeto livro
			Livro livro = new Livro();
			livro.setId(id);
			livro.setTitulo(titulo);
			livro.setAutor(autor);
			livro.setTotalPaginas(paginas);
			livro.setPaginasLidas(paginaAtual);
			livro.setUrlImage(urlImage);

			// Altera o Livro
			dao.altera(livro);

			response.sendRedirect("lista.jsp?resp=sucess&titulo="+titulo);
		} catch (IOException e) {
			try {
				response.sendRedirect("lista.jsp?resp=failure");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dao.close();
		}

	}

}
