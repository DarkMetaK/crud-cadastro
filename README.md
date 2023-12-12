### Fatec - Centro Paula Souza
##### Curso - Desenvolvimento de Software Multiplataforma
##### Estudo de Caso – NodeBounty
O NodeBounty é um projeto de fintech prototipado e desenvolvido no
terceiro semestre de Desenvolvimento de Software Multiplataforma da
Fatec Zona Leste por alunos com um enorme interesse em tecnologia que
visam a qualificação para a entrada no mercado de trabalho. O intento
em centrar nossos esforços em um projeto de fintech é a
inevitabilidade de nichar nossos estudos,
explorando ao máximo linguagens, bibliotecas e frameworks que
corroborem com o progresso de aprendizado. Com o boom dos serviços
financeiros no Brasil, a reinvenção se fez necessária. O que distingue
o Node Bounty das demais conveniências financeiras é a possibilidade
de integrar o advento de aquisições constantes em estabelecimentos específicos em vantagens
reais. Buscamos oferecer o suporte ao público que adquire o mesmo nicho de produto de maneira abundante. 

Time de desenvolvimento
- Dayvid Dalton
- Eloah Baracho
- Matheus Porto
- Daniel Viegas
- Alberto Junior


A arquitetura segue uma abordagem orientada a serviços. Os serviços foram classificados em três tipos (ERL, 2007):
- **1. Serviços utilitários**. Implementam funcionalidades comuns a vários tipos de aplicações, como, por exemplo: log, notificação, transformação de informações. Um exemplo de serviço utilitário é um serviço de conversão de moeda que
poderá ser acessado para calcular a conversão de uma moeda (por exemplo, dólares) para outra (por exemplo, euros).
- **2. Serviços de entidade (serviços de negócios)**. Derivado de uma ou mais entidades de negócio (domínio), possuindo um alto grau de reutilização. Geralmente são serviços que fazem operações CRUD (Create, Read, Update e Delete).
- **3. Serviços de tarefa (coordenação de processos-workflow)**. Tipo de serviço mais específico que possui baixo grau de reuso. Consome outros serviços para atender seus consumidores. São serviços que suportam um processo de negócios
amplo que geralmente envolve atividades e atores diferentes. Um exemplo de serviço de coordenação em uma empresa é um serviço de pedidos em que os pedidos são feitos, os produtos são aceitos e os pagamentos são efetuados.
A visão lógica da arquitetura para API de Cliente é apresentada na figura abaixo. A visã lógica descreve como o código está organizado, as classes os pacotes e os relacionamentos entre eles.

>Referencias
- [1] KRUCHTEN, Philippe. Reference: Title: Architectural blueprints—the “4+ 1” view model of software architecture. IEEE software, v. 12, n. 6, 1995.
- [2] ERL, Thomas. SOA principles of service design (the Prentice Hall service-oriented computing series from Thomas Erl). Prentice Hall PTR, 2007.
- [3] LARMAN, Craig. Utilizando UML e padrões. 2aed., Porto Alegre: Bookman Editora, 2006 (pag. 147).
