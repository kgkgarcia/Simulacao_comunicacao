import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Switch extends Equipamentos implements Serializable{
    private Map<String, Integer> tabelaEnderecamento;
    private int numeroPortas;

    public Switch(String nome, String mac, int numeroPortas) {
        super(nome, null, mac);
        this.tabelaEnderecamento = new HashMap<>();
        this.numeroPortas = numeroPortas;
    }

    public void conectarEquipamento(Equipamentos equipamento, int portaSwitch) {
        String enderecoMAC = equipamento.getMac();
        tabelaEnderecamento.put(enderecoMAC, portaSwitch);
    }

    public void adicionarEnderecoMAC(String enderecoMAC, int porta) {
        tabelaEnderecamento.put(enderecoMAC, porta);
    }

    public int obterPortaPorEnderecoMAC(String enderecoMAC) {
        return tabelaEnderecamento.getOrDefault(enderecoMAC, -1);
    }

    public boolean equipamentoConectado(Equipamentos equipamento) {
        return tabelaEnderecamento.containsKey(equipamento.getMac());
    }

    public Map<String, Integer> getEquipamentosConectados() {
        return new HashMap<>(tabelaEnderecamento);
    }

    public boolean portaOcupada(int porta) {
        return tabelaEnderecamento.containsValue(porta);
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }
    

     // Método para encaminhar dados para o equipamento de destino
     public void encaminharDados(String enderecoMACRemetente, String enderecoMACDestinatario, String dados, Configuracao configuracao) {
        Equipamentos remetente = configuracao.buscarEquipamentoPorMAC(enderecoMACRemetente);
        Equipamentos destinatario = configuracao.buscarEquipamentoPorMAC(enderecoMACDestinatario);

        if (remetente != null && destinatario != null) {
            System.out.println("Switch " + getNome() + " encaminhou dados de " + remetente.getNome() +
                    " para " + destinatario.getNome() + ": " + dados);
            destinatario.receberDados(remetente, dados);
        } else {
            System.out.println("Equipamento remetente ou destinatário não encontrado no Switch.");
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Switch " + getNome() + " - Portas: " + getNumeroPortas() + "\n");

        for (Map.Entry<String, Integer> entry : tabelaEnderecamento.entrySet()) {
            result.append("Endereço MAC: ").append(entry.getKey()).append(", Porta: ").append(entry.getValue())
                    .append("\n");
        }

        return result.toString();
    }
}
