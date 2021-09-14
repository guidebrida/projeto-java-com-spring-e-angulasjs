package projeto.curso.manutencao.services;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.curso.manutencao.domain.*;
import projeto.curso.manutencao.domain.enums.Funcionarios;
import projeto.curso.manutencao.domain.enums.Status;
import projeto.curso.manutencao.domain.enums.TipoCliente;
import projeto.curso.manutencao.repositories.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBservice {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public void instantiateTestDatabase() throws ParseException {

        OrdemDeServico os1 = new OrdemDeServico(null, Status.AGUARDANDOAPROVACAO ,  "https://debrida-projeto-betha.s3.sa-east-1.amazonaws.com/odfoto.jpg");
        OrdemDeServico os2 = new OrdemDeServico(null, Status.APROVADO, "https://debrida-projeto-betha.s3.sa-east-1.amazonaws.com/odfoto.jpg");
        OrdemDeServico os3 = new OrdemDeServico(null , Status.FAZENDOORCAMENENTO ,"https://debrida-projeto-betha.s3.sa-east-1.amazonaws.com/odfoto.jpg");
//instançiacão das ordens de servico


        Equipamento eq1 = new Equipamento(null, "bicleta", "caloi", "bicleta infantil", "Corrente arebentada");
        Equipamento eq2 = new Equipamento(null, "computador", "dell", "Computador para escritorio", "Placa mãe queimada");
        Equipamento eq3 = new Equipamento(null, "skate", "tonyhalk", "jogo", "roda quebrada");
//instanciação dos equipamentos


        Cliente cli1 = new Cliente(null, "Joao pedro folchini", "JPFolchini@gmail.com", "15158370095", "4899001122", TipoCliente.PESSOAFISICA);
        Cliente cli2 = new Cliente(null, "vicenzo de faverri", "vicendoFAV@hotmail.com", "27504060000106", "4896119005", TipoCliente.PESSOAJURIDICA);
//instanciação dos clientes

        Endereco end1 = new Endereco(null, "Rua dos patos", "310", "Casa branca", "centro", "88840000", "Urussanga", "Santa Catarina");
        Endereco end2 = new Endereco(null, "avenida 1880", "100", "Casa amarela", "prospera", "848450646", "Criciuma", "Santa Catarina");
//instacição dos enderecos

        Funcionario func1 = new Funcionario(null, Funcionarios.TECNICO, "tecnico", pe.encode("123"));
        Funcionario func2 = new Funcionario(null, Funcionarios.RECEPCIONISTA, "recepcionista",  pe.encode("123"));
        Funcionario func3 = new Funcionario(null, Funcionarios.ADMIN, "admin",  pe.encode("123"));

        os1.getEquipamentos().addAll(Arrays.asList(eq1));
        os2.getEquipamentos().addAll(Arrays.asList(eq2));
        os3.getEquipamentos().addAll(Arrays.asList(eq3));
//ordens de serviço recendo equipamentos atravez do metodo one to many
        eq1.setOrdemDeServico(os1);
        eq2.setOrdemDeServico(os2);
        eq3.setOrdemDeServico(os3);
//equipamento recebe atravres do metodo manytoone
        os1.setCliente(cli1);
        os2.setCliente(cli2);
        os3.setCliente(cli2);

        cli1.getOrdemDeServico().addAll(Arrays.asList(os1));
        cli2.getOrdemDeServico().addAll(Arrays.asList(os2, os3));

        cli1.setEndereco(end1);
        cli2.setEndereco(end2);

        end1.setCliente(cli1);
        end2.setCliente(cli2);

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        osRepository.saveAll(Arrays.asList(os1, os2, os3));
        equipamentoRepository.saveAll(Arrays.asList(eq1, eq2, eq3));
        enderecoRepository.saveAll(Arrays.asList(end1, end2));
        funcionarioRepository.saveAll(Arrays.asList(func1, func2, func3));


    }
}
