package br.edu.ifsp.application.main;


import br.edu.ifsp.application.repository.inMemory.*;
import br.edu.ifsp.application.repository.sqlite.*;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.entities.trecho.AssentosTrechoLinha;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.passagem.TipoEspecial;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.funcionario.FuncionarioDAO;
import br.edu.ifsp.domain.usecases.funcionario.GerenciarFuncionarioUseCase;
import br.edu.ifsp.domain.usecases.linha.GerenciarLinhaUseCase;
import br.edu.ifsp.domain.usecases.linha.LinhaDAO;
import br.edu.ifsp.domain.usecases.login.FazerLoginUseCase;
import br.edu.ifsp.domain.usecases.login.LoginDAO;
import br.edu.ifsp.domain.usecases.onibus.GerenciarOnibusUseCase;
import br.edu.ifsp.domain.usecases.onibus.OnibusDAO;
import br.edu.ifsp.domain.usecases.passagem.*;
import br.edu.ifsp.domain.usecases.relatorio.EmitirRelatorioDiarioUseCase;
import br.edu.ifsp.domain.usecases.relatorio.EmitirRelatoriosUseCase;
import br.edu.ifsp.domain.usecases.trecho.GerenciarTrechosUseCase;
import br.edu.ifsp.domain.usecases.trecho.TrechoDAO;
import br.edu.ifsp.domain.usecases.trecho.TrechoLinhaDAO;
import br.edu.ifsp.domain.usecases.viagem.GerarViagemUseCase;
import br.edu.ifsp.domain.usecases.viagem.ViagemDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {

    public static GerenciarFuncionarioUseCase gerenciarFuncionarioUseCase;
    public static GerenciarLinhaUseCase gerenciarLinhaUseCase;
    public static FazerLoginUseCase fazerLoginUseCase;
    public static GerenciarOnibusUseCase gerenciarOnibusUseCase;
    public static ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase;
    public static DevolverPassagemUseCase devolverPassagemUseCase;
    public static ReagendarPassagensUseCase reagendarPassagensUseCase;
    public static ReemitirPassagemUseCase reemitirPassagemUseCase;
    public static VenderPassagemUseCase venderPassagemUseCase;
    private static EmitirRelatoriosUseCase emitirRelatoriosUseCase;
    private static EmitirRelatorioDiarioUseCase emitirRelatorioDiarioUseCase;
    public static GerenciarTrechosUseCase gerenciarTrechosUseCase;
    private static GerarViagemUseCase gerarViagemUseCase;


    public static void main(String[] args) throws IOException {

        inMemoryInjection();

        //popularComDadosFalsos();
        setarBancoDeDados();

        WindowLoader.main(args);
        // System.out.println(consultarPassagemVendidaUseCase.consultarPassagemByCpf(passagem1.getCpf()).get().toString());
    }

    private static void popularComDadosFalsos() throws IOException {
        //Login

        //fazerLoginUseCase.loginAsSeller();
        fazerLoginUseCase.createLogin("Gustavo", "Pinatti");
        //System.out.println(fazerLoginUseCase.loginAsAdmin("Pinatti", "Gustavo"));
        //System.out.println(fazerLoginUseCase.loginAsAdmin("pinatti", "Gustavo"));  //SENHA ERRADA
        //System.out.println(fazerLoginUseCase.loginAsAdmin("Pinatti", "nome errado")); //NOME ERRADO

        //Gerenciar Trechos
        Trecho trecho1 = new Trecho("São Paulo", "São Carlos", 290, LocalTime.of(3,30,0,0), 85.00, 5.00, 20.00, "SP-SC");
        Trecho trecho2 = new Trecho("São Carlos", "Ibaté", 10, LocalTime.of(0,20,0,0), 500.00, 15.00, 30.00, "SC-IB");
        Trecho trecho3 = new Trecho("Ibaté", "Araraquara", 50, LocalTime.of(0,45,0,0), 100.00, 15.00, 50.00, "IB-AR");
        Trecho trecho4 = new Trecho("Narnia", "Hogwarts", 50000, LocalTime.of(0,45,0,0), 100.00, 15.00, 50.00, "IB-AR");
        //System.out.println("trecho4 = " + trecho4);
        gerenciarTrechosUseCase.insert(trecho1);
        gerenciarTrechosUseCase.insert(trecho2);
        gerenciarTrechosUseCase.insert(trecho3);
        gerenciarTrechosUseCase.insert(trecho4);

        List<Trecho> trechos = gerenciarTrechosUseCase.getAll();
        //System.out.println("Lista de trechos: " + trechos.size());

        gerenciarTrechosUseCase.delete(trechos.get(3));
        trechos = gerenciarTrechosUseCase.getAll();
        //System.out.println("Lista de trechos após deletar o quarto trecho: " + trechos.size());

        gerenciarTrechosUseCase.insert(trecho4);
        trechos = gerenciarTrechosUseCase.getAll();
        //gerenciarTrechosUseCase.deleteByKey(trechos.get(3).getId());
        //trechos = gerenciarTrechosUseCase.getAll();
        //System.out.println("Lista de trechos após deletar o quarto trecho pela chave: " + trechos.size());
        //gerenciarTrechosUseCase.deleteByKey(UUID.randomUUID());

        trecho2.setQuilometragem(50);
        gerenciarTrechosUseCase.update(trecho2);
        trechos = gerenciarTrechosUseCase.getAll();
        //System.out.println("Alteração de quilometragem do segundo trecho: " + trechos.get(1).getQuilometragem());

        //System.out.println("=====================================================================");

        AssentosTrechoLinha assentosTrechoLinha1 = new AssentosTrechoLinha(LocalDate.of(2020, 1, 12));
        AssentosTrechoLinha assentosTrechoLinha2 = new AssentosTrechoLinha(LocalDate.of(2020, 1, 19));
        AssentosTrechoLinha assentosTrechoLinha3 = new AssentosTrechoLinha(LocalDate.of(2020, 1, 27));
        AssentosTrechoLinha assentosTrechoLinha4 = new AssentosTrechoLinha(LocalDate.of(2020, 1, 30));

        //List<TrechoLinha> trechoLinhaList = gerenciarTrechosUseCase.getAll();
        //System.out.println("=====================================================================");

        Funcionario funcionario1 = new Funcionario("884.295.278-85", "João da Silva", "20.453.187-1", "motorista");
        Funcionario funcionario2 = new Funcionario("021.331.658-78", "Guilherme Oliveira", "24.814.848-5", "motorista");
        Funcionario funcionario3 = new Funcionario("453.625.028-08", "Paula Maria", "33.033.951-5", "vendedor");
        Funcionario funcionario4 = new Funcionario("379.207.788-40", "Mario dos Santos", "30.791.137-8", "vendedor");

        gerenciarFuncionarioUseCase.insert(funcionario1);
        gerenciarFuncionarioUseCase.insert(funcionario2);
        gerenciarFuncionarioUseCase.insert(funcionario3);
        gerenciarFuncionarioUseCase.insert(funcionario4);

        List<Funcionario> funcionarios = gerenciarFuncionarioUseCase.getAll();
        //System.out.println("Lista de funcionarios: " + funcionarios.size());

        gerenciarFuncionarioUseCase.delete(funcionarios.get(2));
        funcionarios = gerenciarFuncionarioUseCase.getAll();
        //System.out.println("Lista de trechos após deletar o terceiro funcionario: " + funcionarios.size());

        gerenciarFuncionarioUseCase.insert(funcionario3);
        gerenciarFuncionarioUseCase.deleteByKey(funcionarios.get(2).getCpf());
        funcionarios = gerenciarFuncionarioUseCase.getAll();
        //System.out.println("Lista de funcionarios após deletar o terceiro funcionario pela chave: " + funcionarios.size());

        funcionario2.setCargo("vendedor");
        gerenciarFuncionarioUseCase.edit(funcionario2);
        funcionarios = gerenciarFuncionarioUseCase.getAll();
        //System.out.println("Alteração do cargo do terceiro funcionario: " + funcionarios.get(1).getCargo());

        //System.out.println("=====================================================================");

        Onibus onibus1 = new Onibus("37279891716", "FMH8701");
        Onibus onibus2 = new Onibus("31532184998", "DUQ7313");
        Onibus onibus3 = new Onibus("06520602420", "FOL1994");
        Onibus onibus4 = new Onibus("25737051391", "DQU0144");

        gerenciarOnibusUseCase.insert(onibus1);
        gerenciarOnibusUseCase.insert(onibus2);
        gerenciarOnibusUseCase.insert(onibus3);
        gerenciarOnibusUseCase.insert(onibus4);

        List<Onibus> onibus = gerenciarOnibusUseCase.getAll();
        //System.out.println("Lista de onibus: " + onibus.size());

        gerenciarOnibusUseCase.delete(onibus.get(2));
        onibus = gerenciarOnibusUseCase.getAll();
        //System.out.println("Lista de onibus após deletar o terceiro onibus: " + onibus.size());

        gerenciarOnibusUseCase.insert(onibus3);
        gerenciarOnibusUseCase.deleteByKey(onibus.get(2).getRenavam());
        onibus = gerenciarOnibusUseCase.getAll();
        //System.out.println("Lista de onibus após deletar o terceiro onibus pela chave: " + onibus.size());

        onibus2.setPlaca("BHU3072");
        gerenciarOnibusUseCase.update(onibus2);
        onibus = gerenciarOnibusUseCase.getAll();
        //System.out.println("Alteração da placa do terceiro onibus: " + onibus.get(1).getPlaca());

        //System.out.println("=====================================================================");


        Linha linha1 = new Linha(22L, "linha1");
        Linha linha2 = new Linha(39L, "linha2");
        Linha linha3 = new Linha(55L, "linha3");
        Linha linha4 = new Linha(12L, "linha4");

        gerenciarLinhaUseCase.insert(linha1);
        gerenciarLinhaUseCase.insert(linha2);
        gerenciarLinhaUseCase.insert(linha3);
        gerenciarLinhaUseCase.insert(linha4);

        TrechoLinha trechoLinha1 = new TrechoLinha(LocalTime.of(9,30,0,0),1,linha1,assentosTrechoLinha1,trecho1);
        TrechoLinha trechoLinha2 = new TrechoLinha(LocalTime.of(11,30,0,0),2,linha1,assentosTrechoLinha2,trecho2);
        TrechoLinha trechoLinha3 = new TrechoLinha(LocalTime.of(13,30,0,0),3,linha1,assentosTrechoLinha3,trecho3);

        gerenciarLinhaUseCase.addTrechoLinha(trechoLinha1);
        gerenciarLinhaUseCase.addTrechoLinha(trechoLinha2);
        gerenciarLinhaUseCase.addTrechoLinha(trechoLinha3);

        List<Linha> linhas = gerenciarLinhaUseCase.getAll();
        //System.out.println("Lista de linhas: " + linhas.size());

        gerenciarLinhaUseCase.delete(linhas.get(2));
        linhas = gerenciarLinhaUseCase.getAll();
        //System.out.println("Lista de linhas após deletar a terceira linha: " + linhas.size());

        gerenciarLinhaUseCase.insert(linha3);
        gerenciarLinhaUseCase.deleteByKey(linhas.get(2).getId());
        linhas = gerenciarLinhaUseCase.getAll();
        //System.out.println("Lista de linhas após deletar a terceira linha pela chave: " + linhas.size());

        linha2.setNome("novoNomeLinha");
        gerenciarLinhaUseCase.update(linha2);
        linhas = gerenciarLinhaUseCase.getAll();
        //System.out.println("Alteração do nome da segunda linha: " + linhas.get(1).getNome());

        //System.out.println("=====================================================================");

        Viagem viagem1 = gerarViagemUseCase.gerarViagem(LocalDate.of(2020, 1, 10), "São Paulo", "São Carlos", LocalTime.of(4,30,0,0));
        Viagem viagem2 = gerarViagemUseCase.gerarViagem(LocalDate.of(2020, 1, 16), "São Carlos", "Ibaté", LocalTime.of(3,0,0,0));
        Viagem viagem3 = gerarViagemUseCase.gerarViagem(LocalDate.of(2020, 1, 22), "Ibaté", "Araraquara", LocalTime.of(5,15,0,0));


        Passagem passagem1 = venderPassagemUseCase.venderPassagem("São Paulo", "São Carlos", LocalDate.of(2021, 1, 12), LocalTime.of(3,30,0,0), "13", "Juca da Silva", "464.567.370-01", "35.938.378-6", "(74) 21059-6913", true, TipoEspecial.DEFICIENTE);
        Passagem passagem2 = venderPassagemUseCase.venderPassagem("São Carlos", "Ibaté", LocalDate.of(2021, 1, 12), LocalTime.of(3,30,0,0), "17", "Marcos Oliveira", "071.853.200-70", "23.703.707-5", "(32) 55342-0093", false, TipoEspecial.IDOSO);
        Passagem passagem3 = venderPassagemUseCase.venderPassagem("Ibaté", "Araraquara", LocalDate.of(2021, 2, 15), LocalTime.of(3,30,0,0), "22", "Mario Medeiros", "227.837.680-20", "45.372.855-8", "(19) 43202-0775", false, TipoEspecial.NAO);

        //System.out.println("passagem3 = " + passagem3);
        
        //passagem2 = reagendarPassagensUseCase.reagendar(passagem2.getNumPassagem(), LocalDate.of(2021, 1, 17));
        //System.out.println("Reagendamento da passagem 2 alterando a data: " + passagem2.getViagem().getData());

        //passagem3 = reemitirPassagemUseCase.reemitirPassagem(passagem3.getCpf()).get();
        //System.out.println("Reemitindo a passagem 3: " + passagem3);

        //System.out.println("Devolvendo a passagem 1");
        //devolverPassagemUseCase.devolverPassagem(consultarPassagemVendidaUseCase.consultarPassagem(passagem1.getNumPassagem()));
        //consultarPassagemVendidaUseCase.consultarPassagem(passagem1.getNumPassagem());


        //System.out.println("=====================================================================");

        //Relatorio relatorio = emitirRelatoriosUseCase.gerarRelatorio(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 31));
        //relatorio.salvarEmArquivo();
        //System.out.println("gerenciarTrechosUseCase.getAll() = " + gerenciarTrechosUseCase.getAll());
    }

    private static void inMemoryInjection(){
        FuncionarioDAO funcionarioDAO = new SqliteFuncionarioDAO();
        LinhaDAO linhaDAO = new SqliteLinhaDAO();
        LoginDAO loginDAO = new InMemoryLoginDAO();
        OnibusDAO onibusDAO = new SqliteOnibusDAO();
        PassagemDAO passagemDAO = new InMemoryPassagemDAO();
        TrechoDAO trechoDAO = new SqliteTrechoDAO();
        TrechoLinhaDAO trechoLinhaDAO = new InMemoryTrechoLinhaDAO();
        ViagemDAO viagemDAO = new InMemoryViagemDAO();

        gerenciarFuncionarioUseCase = new GerenciarFuncionarioUseCase(funcionarioDAO);
        gerenciarLinhaUseCase = new GerenciarLinhaUseCase(linhaDAO, trechoLinhaDAO);
        fazerLoginUseCase = new FazerLoginUseCase(loginDAO);
        gerenciarOnibusUseCase = new GerenciarOnibusUseCase(onibusDAO);
        consultarPassagemVendidaUseCase = new ConsultarPassagemVendidaUseCase(passagemDAO);
        gerarViagemUseCase = new GerarViagemUseCase(linhaDAO,trechoDAO,trechoLinhaDAO, viagemDAO);
        venderPassagemUseCase = new VenderPassagemUseCase(passagemDAO,gerarViagemUseCase);
        devolverPassagemUseCase = new DevolverPassagemUseCase(passagemDAO);
        reagendarPassagensUseCase = new ReagendarPassagensUseCase(passagemDAO, venderPassagemUseCase);
        reemitirPassagemUseCase = new ReemitirPassagemUseCase(passagemDAO);
        emitirRelatoriosUseCase = new EmitirRelatoriosUseCase(viagemDAO, trechoLinhaDAO);
        emitirRelatorioDiarioUseCase = new EmitirRelatorioDiarioUseCase(viagemDAO, trechoLinhaDAO);
        gerenciarTrechosUseCase = new GerenciarTrechosUseCase(trechoDAO, trechoLinhaDAO);

    }

    private static void setarBancoDeDados(){
        BuildDatabase db = new BuildDatabase();
        db.buildDatabaseIfMissing();
        System.out.println("Tabelas criadas");
    }
}
