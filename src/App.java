import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuracao configuracao = new Configuracao();

        while (true) {
            exibirMenuPrincipal();
            System.out.print("Escolha uma opção: ");
            int opcaoPrincipal = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcaoPrincipal) {
                case 1:
                    exibirMenuEquipamentos();
                    int opcaoEquipamento = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    switch (opcaoEquipamento) {
                        case 1: // Inserir Equipamento Terminal
                            System.out.println("Inserir Equipamento Terminal:");
                            System.out.print("Nome: ");
                            String nomeEquipamentoT = scanner.nextLine();

                            String macEquipamentoT;
                            do {
                                System.out.print("MAC: ");
                                macEquipamentoT = scanner.nextLine();

                                if (!configuracao.verificarMacUnico(macEquipamentoT)) {
                                    System.out.println("MAC já existe. Escolha outro.");
                                }
                            } while (!configuracao.verificarMacUnico(macEquipamentoT));

                            Equipamentos equipamentoTerminal = new Equipamentos(nomeEquipamentoT, null,
                                    macEquipamentoT);
                            String enderecoIP = equipamentoTerminal.getIP();
                            equipamentoTerminal.setIP(enderecoIP);

                            configuracao.adicionarEquipamento(equipamentoTerminal);
                            System.out.println("Equipamento Terminal adicionado com sucesso.");
                            break;

                        case 2: // Inserir Switch
                            System.out.println("Inserir Switch:");
                            System.out.print("Nome: ");
                            String nomeSwitch = scanner.nextLine();

                            String macSwitch;
                            do {
                                System.out.print("MAC: ");
                                macSwitch = scanner.nextLine();

                                if (!configuracao.verificarMacUnico(macSwitch)) {
                                    System.out.println("MAC já existe. Escolha outro.");
                                }
                            } while (!configuracao.verificarMacUnico(macSwitch));

                            System.out.print("Número de Portas: ");
                            int numeroPortasSwitch = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer do scanner

                            Switch switchEquipamento = new Switch(nomeSwitch, macSwitch, numeroPortasSwitch);
                            configuracao.adicionarSwitch(switchEquipamento);
                            System.out.println("Switch adicionado com sucesso.");
                            break;

                        case 0: // Voltar
                            break;

                        default:
                            System.out.println("Opção inválida. Tente novamente.\n");
                            break;
                    }
                    break;

                case 2:
                    System.out.print("Nome do Equipamento: ");
                    String nomeEquipamento = scanner.nextLine();

                    System.out.print("Nome do Switch: ");
                    String nomeSwitch = scanner.nextLine();

                    Equipamentos equipamento = configuracao.buscarEquipamentoPorNome(nomeEquipamento);
                    Switch switchEquipamento = configuracao.buscarSwitchPorNome(nomeSwitch);

                    if (equipamento != null && switchEquipamento != null) {
                        if (switchEquipamento.equipamentoConectado(equipamento)) {
                            System.out.println("Este equipamento já está conectado a este switch.");
                        } else {
                            int portaSwitch;

                            do {
                                System.out.print("Porta do Switch (1-" + switchEquipamento.getNumeroPortas() + "): ");
                                portaSwitch = scanner.nextInt();
                                scanner.nextLine(); // Limpar o buffer do scanner

                                if (portaSwitch < 1 || portaSwitch > switchEquipamento.getNumeroPortas()) {
                                    System.out.println("Porta inválida. Tente novamente.");
                                } else if (switchEquipamento.portaOcupada(portaSwitch)) {
                                    System.out.println("Esta porta já está ocupada. Escolha outra porta.");
                                }
                            } while (portaSwitch < 1 || portaSwitch > switchEquipamento.getNumeroPortas()
                                    || switchEquipamento.portaOcupada(portaSwitch));

                            switchEquipamento.conectarEquipamento(equipamento, portaSwitch);
                            System.out.println("Equipamento conectado ao Switch com sucesso.");
                        }
                    } else {
                        System.out.println("Equipamento ou Switch não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Nome do Equipamento Remetente: ");
                    String nomeEquipamentoRemetente = scanner.nextLine();

                    System.out.print("Nome do Equipamento Destinatário: ");
                    String nomeEquipamentoDestinatario = scanner.nextLine();

                    System.out.print("Dados a serem enviados: ");
                    String dados = scanner.nextLine();

                    configuracao.simularEnvioDados(nomeEquipamentoRemetente, nomeEquipamentoDestinatario, dados);
                    break;

                case 4:
                    configuracao.salvarConfiguracoes("configuracoes.bin");
                    System.out.println("Configurações salvas com sucesso.");
                    break;

                case 5:
                    configuracao.imprimirConfiguracao();
                    break;

                case 6:
                    configuracao.restaurarConfiguracoes("configuracoes.bin");
                    System.out.println("Configurações restauradas com sucesso.");
                    break;
                case 7:
                    // Imprimir Tabela ARP de um Equipamento
                    System.out.print("Nome do Equipamento: ");
                    String nomeEquipamentoARP = scanner.nextLine();

                    Equipamentos equipamentoARP = configuracao.buscarEquipamentoPorNome(nomeEquipamentoARP);

                    if (equipamentoARP != null) {
                        equipamentoARP.imprimirTabelaARP();
                    } else {
                        System.out.println("Equipamento não encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    System.exit(0);

                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Inserir equipamentos");
        System.out.println("2 - Configurar rede");
        System.out.println("3 - Simular envio de dados");
        System.out.println("4 - Guardar configurações");
        System.out.println("5 - Imprimir configurações");
        System.out.println("6 - Restaurar configurações");
        System.out.println("7 - Ver Tabela ARP");
        System.out.println("0 - Sair");
    }

    private static void exibirMenuEquipamentos() {
        System.out.println("\n=== MENU EQUIPAMENTOS ===");
        System.out.println("1 - Inserir Equipamento Terminal");
        System.out.println("2 - Inserir Switch");
        System.out.println("0 - Voltar");
    }
}
