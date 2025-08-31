package ti2cc;

public class Funcionario {
    private int id;
    private String nome;
    private int idade;
    private String funcao;
    private char sexo;
    
    public Funcionario() {
        this.id = 1;
        this.nome = "";
        this.idade = 1;
        this.funcao = "";
        this.sexo = '*';
    }
    
    public Funcionario(int id, String nome, int idade, String funcao, char sexo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.funcao = funcao;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Funcionario [id=" + id + ", nome=" + nome + ", idade=" + idade + ", funcao=" + funcao + ", sexo=" + sexo + "]";
    }    
}