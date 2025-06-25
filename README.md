# Simulação de Comunicação entre Equipamentos Terminais

Este projeto em Java simula a comunicação entre equipamentos terminais, como computadores e switches, em uma rede. O sistema permite a configuração da rede, inserção de equipamentos, simulação de envio de dados entre os terminais, exibição de tabelas ARP e muito mais.

## Funcionalidades

### Menu Principal
O menu principal oferece as seguintes opções:

1. **Inserir Equipamentos**: Adiciona equipamentos, como computadores e switches, à rede.
2. **Configurar Rede**: Conecta os equipamentos a switches e configura a rede.
3. **Simular Envio de Dados**: Simula o envio de dados entre os equipamentos terminais.
4. **Guardar Configurações**: Salva as configurações atuais da rede.
5. **Imprimir Configurações**: Exibe as configurações atuais, como equipamentos e switches conectados.
6. **Restaurar Configurações**: Restaura as configurações salvas.
7. **Ver Tabela ARP**: Exibe a tabela ARP de um equipamento, mostrando a correspondência entre IP e MAC.
0. **Sair**: Finaliza o programa.

### Menu de Equipamentos
No menu de equipamentos, você pode:

1. **Inserir Equipamento Terminal**: Adiciona um novo terminal (equipamento) à rede.
2. **Inserir Switch**: Adiciona um novo switch à rede.
0. **Voltar**: Retorna ao menu principal.

## Exemplos de Uso

### Inserção de Equipamentos
Ao inserir um novo equipamento terminal, você será solicitado a fornecer:

- **Nome do Equipamento**: Nome identificador do equipamento (ex. `pc1`).
- **MAC Address**: O endereço MAC do equipamento.

Exemplo de saída:
Nome: pc1
MAC: scsdssf
Equipamento Terminal adicionado com sucesso.


### Conexão entre Equipamentos e Switches
Após adicionar os equipamentos, você pode conectá-los aos switches, especificando a porta do switch.

Exemplo de saída:
Nome do Equipamento: pc1
Nome do Switch: sw1
Porta do Switch (1-24): 2
Equipamento conectado ao Switch com sucesso.


### Simulação de Envio de Dados
Você pode simular o envio de dados entre dois equipamentos terminais.

### Exibição da Tabela ARP
A tabela ARP de um equipamento pode ser exibida, mostrando as correspondências de IPs e MACs.

### Impressão das Configurações
O sistema pode exibir a lista de equipamentos conectados e switches.


## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Estruturas de Dados**: Uso de listas, mapas e arrays para gerenciar os equipamentos, switches e tabelas ARP.
- **Programação Orientada a Objetos**: Classes para representar os equipamentos, switches e a rede.

## Como Executar

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/repositorio.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd repositorio
    ```

3. Compile o código:
    ```bash
    javac Main.java
    ```

4. Execute o programa:
    ```bash
    java Main
    ```



