import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Equipamentos  implements Serializable{

    private String nome;
    private String ip;
    private String mac;
    private Map<String, String> tabelaARP;

    public Equipamentos(String nome, String ip, String mac) {
        this.nome = nome;
        this.ip = getIP();
        this.mac = mac;
        this.tabelaARP = new HashMap<>();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
      public void setMac(String mac) {
        this.mac = mac;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public String getIP() {
        Random rand = new Random();

        // O último número do IP será um número aleatório entre 1 e 254
        int ultimoNumero = rand.nextInt(254) + 1;

        // Constrói o endereço IP
        String ip = String.format("192.168.1.%d", ultimoNumero);

        // Define o IP no atributo da classe
        this.ip = ip;

        return ip;
    }

    public String getMac() {
        return this.mac;
    }


    @Override
    public String toString() {
        String str = String.format("%-10s | %-17s | %-17s", "Nome", "IP", "MAC");
        str += "\n" + String.format("%-10s | %-17s | %-17s\n", this.nome, this.ip, this.mac);
        return str;
    }

    public void enviarDados(Equipamentos destinatario, String dados) {
        System.out.println(this.nome + " enviou dados para " + destinatario.getNome() + ": " + dados);
        destinatario.receberDados(this, dados);

        // Atualizar a tabela ARP do remetente
        destinatario.adicionarEntradaARP(this.ip, this.mac);
    }

    // Método para receber dados (simula o processo de recebimento)
    public void receberDados(Equipamentos remetente, String dados) {
        System.out.println(this.nome + " recebeu dados de " + remetente.getNome() + ": " + dados);

        // Atualizar a tabela ARP do destinatário
        remetente.adicionarEntradaARP(this.ip, this.mac);
    }

    public void adicionarEntradaARP(String enderecoIP, String enderecoMAC) {
        tabelaARP.put(enderecoIP, enderecoMAC);
    }

    // Obtém o MAC correspondente a um IP da tabela ARP
    public String obterMACPorEnderecoIP(String enderecoIP) {
        return tabelaARP.get(enderecoIP);
    }

    /*Imprimir a tabela ARP do equipamento */
    public void imprimirTabelaARP() {
        System.out.println("Tabela ARP de " + this.nome + ":");
        System.out.printf("%-17s | %-17s\n", "Endereço IP", "Endereço MAC");
    
        for (Map.Entry<String, String> entry : tabelaARP.entrySet()) {
            String enderecoIP = entry.getKey();
            String enderecoMAC = entry.getValue();
            System.out.printf("%-17s | %-17s\n", enderecoIP, enderecoMAC);
        }
        System.out.println();
    }
}