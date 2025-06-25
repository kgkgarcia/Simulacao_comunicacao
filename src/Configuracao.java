
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class Configuracao implements Serializable {
    private List<Equipamentos> equipamentos;
    private List<Switch> switches;

    public Configuracao() {
        this.equipamentos = new ArrayList<>();
        this.switches = new ArrayList<>();
    }

    public void adicionarEquipamento(Equipamentos equipamento) {
        equipamentos.add(equipamento);
    }

    public void adicionarSwitch(Switch switchEquipamento) {
        switches.add(switchEquipamento);
    }

    public void conectarEquipamentoASwitch(Equipamentos equipamento, Switch switchEquipamento, int portaSwitch) {
        if (equipamentos.contains(equipamento) && switches.contains(switchEquipamento)) {
            // Conectar equipamento a switch
            switchEquipamento.conectarEquipamento(equipamento, portaSwitch);
        }
    }

    public Equipamentos buscarEquipamentoPorNome(String nomeEquipamento) {
        for (Equipamentos equipamento : equipamentos) {
            if (equipamento.getNome().equals(nomeEquipamento)) {
                return equipamento;
            }
        }
        return null; // Equipamento não encontrado
    }

    public Switch buscarSwitchPorNome(String nomeSwitch) {
        for (Switch switchEquipamento : switches) {
            if (switchEquipamento.getNome().equals(nomeSwitch)) {
                return switchEquipamento;
            }
        }
        return null; // Switch não encontrado
    }

    public boolean verificarMacUnico(String mac) {
        for (Equipamentos equipamento : equipamentos) {
            if (equipamento.getMac().equals(mac)) {
                return false; // MAC já existe
            }
        }

        for (Switch switchEquipamento : switches) {
            if (switchEquipamento.getMac().equals(mac)) {
                return false; // MAC já existe
            }
        }

        return true; // MAC é único
    }

    // metodo para salvar as configuracoes
    public void salvarConfiguracoes(String nomeFicheiro) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFicheiro))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Erro ao salvar as configurações: " + e.getMessage());
        }
    }
    // metodo para restaurar as configuracoes
    public void restaurarConfiguracoes(String nomeFicheiro) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
            Configuracao configuracaoRestaurada = (Configuracao) ois.readObject();
            this.equipamentos = configuracaoRestaurada.equipamentos;
            this.switches = configuracaoRestaurada.switches;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao restaurar as configurações: " + e.getMessage());
        }
    }
    // mostrar como a rede está configurada
    public void imprimirConfiguracao() {
        System.out.println("Equipamentos:");
        for (Equipamentos equipamento : equipamentos) {
            System.out.println(equipamento);
        }

        System.out.println("\nSwitches:");
        for (Switch switchEquipamento : switches) {
            System.out.println(switchEquipamento);
            System.out.println("Equipamentos Conectados:");
            Map<String, Integer> equipamentosConectados = switchEquipamento.getEquipamentosConectados();
            for (Map.Entry<String, Integer> entry : equipamentosConectados.entrySet()) {
                String enderecoMAC = entry.getKey();
                int portaSwitch = entry.getValue();
                Equipamentos equipamentoConectado = buscarEquipamentoPorMAC(enderecoMAC);

                if (equipamentoConectado != null) {
                    System.out
                            .println("   Conectado a: " + equipamentoConectado.getNome() + " | Porta: " + portaSwitch);
                }
            }
            System.out.println();
        }
    }

    // Método auxiliar para buscar um equipamento por endereço MAC
    public Equipamentos buscarEquipamentoPorMAC(String mac) {
        for (Equipamentos equipamento : equipamentos) {
            if (equipamento.getMac().equals(mac)) {
                return equipamento;
            }
        }
        return null;
    }

    // metodo para simular envio de dados
    public void simularEnvioDados(String nomeEquipamentoRemetente, String nomeEquipamentoDestinatario, String dados) {
        Equipamentos remetente = buscarEquipamentoPorNome(nomeEquipamentoRemetente);
        Equipamentos destinatario = buscarEquipamentoPorNome(nomeEquipamentoDestinatario);

        if (remetente != null && destinatario != null) {
            remetente.enviarDados(destinatario, dados);
        } else {
            System.out.println("Equipamento remetente ou destinatário não encontrado.");
        }
    }

}
