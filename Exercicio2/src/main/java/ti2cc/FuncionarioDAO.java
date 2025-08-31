package ti2cc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO extends DAO {
    
    public FuncionarioDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
    
    
    public boolean insert(Funcionario funcionario) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO funcionario (id, nome, idade, funcao, sexo) "
                       + "VALUES ("+funcionario.getId() + ", '" + funcionario.getNome() + "', '"  
                       + funcionario.getIdade() + "', '" + funcionario.getFuncao() + "', '" 
                       + funcionario.getSexo() +  "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    
    public Funcionario get(int id) {
        Funcionario funcionario = null;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM funcionario WHERE id=" + id;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);    
            if(rs.next()){            
                 funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("funcao"), rs.getString("sexo").charAt(0));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return funcionario;
    }
    
    
    public List<Funcionario> get() {
        return get("");
    }

    
    public List<Funcionario> getOrderById() {
        return get("id");        
    }
    
    
    public List<Funcionario> getOrderByNome() {
        return get("nome");        
    }
    
    
    public List<Funcionario> getOrderByFuncao() {
        return get("funcao");        
    }
    
    
    public List<Funcionario> getOrderByIdade() {
        return get("idade");        
    }
    
    
    public List<Funcionario> getOrderBySexo() {
        return get("sexo");        
    }
    
    
    private List<Funcionario> get(String orderBy) {    
    
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM funcionario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);               
            while(rs.next()) {                    
                Funcionario f = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("funcao"), rs.getString("sexo").charAt(0));
                funcionarios.add(f);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return funcionarios;
    }


    public List<Funcionario> getSexoMasculino() {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM funcionario WHERE funcionario.sexo LIKE 'M'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);               
            while(rs.next()) {                    
                Funcionario f = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("funcao"), rs.getString("sexo").charAt(0));
                funcionarios.add(f);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return funcionarios;
    }
    
    
    public boolean update(Funcionario funcionario) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE funcionario SET nome = '" + funcionario.getNome() + "', idade = '"  
                       + funcionario.getIdade() + "', funcao = '" + funcionario.getFuncao() + "', sexo = '" 
                       + funcionario.getSexo() + "' WHERE id = " + funcionario.getId();
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    public boolean delete(int id) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM funcionario WHERE id = " + id;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
}