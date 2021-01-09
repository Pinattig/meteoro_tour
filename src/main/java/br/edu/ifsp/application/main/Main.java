package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.*;
import br.edu.ifsp.domain.entities.trecho.AssentosTrechoLinha;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.usecases.funcionario.FuncionarioDAO;
import br.edu.ifsp.domain.usecases.funcionario.GerenciarFuncionarioUseCase;
import br.edu.ifsp.domain.usecases.linha.GerenciarLinhaUseCase;
import br.edu.ifsp.domain.usecases.linha.LinhaDAO;
import br.edu.ifsp.domain.usecases.login.AutenticarSenhaUseCase;
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

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main {

    private static GerenciarFuncionarioUseCase gerenciarFuncionarioUseCase;
    private static GerenciarLinhaUseCase gerenciarLinhaUseCase;
    private static FazerLoginUseCase fazerLoginUseCase;
    private static GerenciarOnibusUseCase gerenciarOnibusUseCase;
    private static ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase;
    private static DevolverPassagemUseCase devolverPassagemUseCase;
    private static ReagendarPassagensUseCase reagendarPassagensUseCase;
    private static ReemitirPassagemUseCase reemitirPassagemUseCase;
    private static VenderPassagemUseCase venderPassagemUseCase;
    private static EmitirRelatoriosUseCase emitirRelatoriosUseCase;
    private static EmitirRelatorioDiarioUseCase emitirRelatorioDiarioUseCase;
    private static GerenciarTrechosUseCase gerenciarTrechosUseCase;
    private static GerarViagemUseCase gerarViagemUseCase;

    public static void main(String[] args) {

        inMemoryInjection();

        //Login
        //fazerLoginUseCase.createLogin("Gustavo", "Pinatti");
        //System.out.println(fazerLoginUseCase.loginAsAdmin("Pinatti", "Gustavo"));
        //System.out.println(fazerLoginUseCase.loginAsAdmin("pinatti", "Gustavo"));
        //System.out.println(fazerLoginUseCase.loginAsAdmin("Pinatti", "nome errado"));

        //Gerenciar Trechos
        Trecho trecho1 = new Trecho("São Paulo", "São Carlos", 290, LocalTime.of(3,30,0,0), 85.00, 5.00, 20.00, "SP-SC");
        Trecho trecho2 = new Trecho("São Carlos", "Ibaté", 10, LocalTime.of(0,20,0,0), 500.00, 15.00, 30.00, "SC-IB");
        Trecho trecho3 = new Trecho("Ibaté", "Araraquara", 50, LocalTime.of(0,45,0,0), 100.00, 15.00, 50.00, "IB-AR");
        Trecho trecho4 = new Trecho("Narnia", "Hogwarts", 50000, LocalTime.of(0,45,0,0), 100.00, 15.00, 50.00, "IB-AR");

        gerenciarTrechosUseCase.insert(trecho1);
        gerenciarTrechosUseCase.insert(trecho2);
        gerenciarTrechosUseCase.insert(trecho3);
        gerenciarTrechosUseCase.insert(trecho4);

        List<Trecho> trechos = gerenciarTrechosUseCase.getAll();
        System.out.println(trechos.size());

        gerenciarTrechosUseCase.delete(trechos.get(3));
        trechos = gerenciarTrechosUseCase.getAll();
        System.out.println(trechos.size());

        gerenciarTrechosUseCase.insert(trecho4);
        gerenciarTrechosUseCase.deleteByKey(trechos.get(2).getId());
        trechos = gerenciarTrechosUseCase.getAll();
        System.out.println(trechos.size());
        gerenciarTrechosUseCase.deleteByKey(UUID.randomUUID());

        trecho2.setQuilometragem(50);
        gerenciarTrechosUseCase.update(trecho2);
        trechos = gerenciarTrechosUseCase.getAll();
        System.out.println(trechos.get(1).getQuilometragem());

    }

    private static void inMemoryInjection(){
        FuncionarioDAO funcionarioDAO = new InMemoryFuncionarioDAO();
        LinhaDAO linhaDAO = new InMemoryLinhaDAO();
        LoginDAO loginDAO = new InMemoryLoginDAO();
        OnibusDAO onibusDAO = new InMemoryOnibusDAO();
        PassagemDAO passagemDAO = new InMemoryPassagemDAO();
        TrechoDAO trechoDAO = new InMemoryTrechoDAO();
        TrechoLinhaDAO trechoLinhaDAO = new InMemoryTrechoLinhaDAO();
        ViagemDAO viagemDAO = new InMemoryViagemDAO();

        gerenciarFuncionarioUseCase = new GerenciarFuncionarioUseCase(funcionarioDAO);
        gerenciarLinhaUseCase = new GerenciarLinhaUseCase(linhaDAO);
        fazerLoginUseCase = new FazerLoginUseCase(loginDAO);
        gerenciarOnibusUseCase = new GerenciarOnibusUseCase(onibusDAO);
        consultarPassagemVendidaUseCase = new ConsultarPassagemVendidaUseCase(passagemDAO);
        gerarViagemUseCase = new GerarViagemUseCase(linhaDAO,trechoDAO,trechoLinhaDAO);
        venderPassagemUseCase = new VenderPassagemUseCase(passagemDAO,gerarViagemUseCase);
        devolverPassagemUseCase = new DevolverPassagemUseCase(passagemDAO);
        reagendarPassagensUseCase = new ReagendarPassagensUseCase(passagemDAO, venderPassagemUseCase);
        reemitirPassagemUseCase = new ReemitirPassagemUseCase(passagemDAO);
        emitirRelatoriosUseCase = new EmitirRelatoriosUseCase(viagemDAO);
        emitirRelatorioDiarioUseCase = new EmitirRelatorioDiarioUseCase(viagemDAO);
        gerenciarTrechosUseCase = new GerenciarTrechosUseCase(trechoDAO, trechoLinhaDAO);

    }
}
