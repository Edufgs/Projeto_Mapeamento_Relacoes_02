package persistencia;

public class PersistenciaException extends Exception {

	private static final long serialVersionUID = 1L; //Constante para definir a versÃ£o do objeto que vai transitar pela rede. O eclipse pede isso
	
	public PersistenciaException() {
        super("Erro ocorrido na manipulação do banco de dados");
    }

    public PersistenciaException(String string) {
        super(string);
    }
}
