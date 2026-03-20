package com.example.demo.ConfigTest;

import com.example.demo.DTO.Credencial.CredencialCreateDTO;
import com.example.demo.DTO.Cupom.CupomCreateDTO;
import com.example.demo.DTO.Endereco.EnderecoCreateDTO;
import com.example.demo.DTO.Produto.ProdutoCreateDTO;
import com.example.demo.DTO.Usuario.UsuarioCreateDTO;
import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataSeeder {
    @Bean
    @Order(1)
    CommandLineRunner seedUsuarios(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                UsuarioCreateDTO usuarioDTO = new UsuarioCreateDTO("Richard", "Alves", "21123456789", LocalDate.of(2006, 7, 27));
                Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
                usuarioRepository.save(usuario);

                usuarioDTO = new UsuarioCreateDTO("Taylan", "Gonzaga", "21123456789", LocalDate.of(2006, 4, 2));
                usuario = modelMapper.map(usuarioDTO, Usuario.class);
                usuarioRepository.save(usuario);

                usuarioDTO = new UsuarioCreateDTO("Juan", "Santos", "21123456789", LocalDate.of(2006, 1, 29));
                usuario = modelMapper.map(usuarioDTO, Usuario.class);
                usuarioRepository.save(usuario);

                usuarioDTO = new UsuarioCreateDTO("Guilherme", "Pirozi", "21123456789", LocalDate.of(2005, 6, 22));
                usuario = modelMapper.map(usuarioDTO, Usuario.class);
                usuarioRepository.save(usuario);

                usuarioDTO = new UsuarioCreateDTO("Lucas", "Pereira", "21123456789", LocalDate.of(2001, 5, 26));
                usuario = modelMapper.map(usuarioDTO, Usuario.class);
                usuarioRepository.save(usuario);
            }
        };
    }
    @Bean
    @Order(2)
    CommandLineRunner seedCredencial(CredencialRepository credencialRepository, ModelMapper modelMapper) {
        return args -> {
            if (credencialRepository.count() == 0) {
                CredencialCreateDTO credencialDTO = new CredencialCreateDTO("richard.alves@gmail.com", "1234567890");
                Credencial credencial = modelMapper.map(credencialDTO, Credencial.class);
                credencialRepository.save(credencial);

                credencialDTO = new CredencialCreateDTO("taylan.gonzaga@gmail.com", "1234567890");
                credencial = modelMapper.map(credencialDTO, Credencial.class);
                credencialRepository.save(credencial);

                credencialDTO = new CredencialCreateDTO("juan.santos@gmail.com", "1234567890");
                credencial = modelMapper.map(credencialDTO, Credencial.class);
                credencialRepository.save(credencial);

                credencialDTO = new CredencialCreateDTO("guilherme.pirozi@gmail.com", "1234567890");
                credencial = modelMapper.map(credencialDTO, Credencial.class);
                credencialRepository.save(credencial);

                credencialDTO = new CredencialCreateDTO("lucas.pereira@gmail.com", "1234567890");
                credencial = modelMapper.map(credencialDTO, Credencial.class);
                credencialRepository.save(credencial);
            }
        };
    }
    @Bean
    @Order(3)
    CommandLineRunner seedEndereco(EnderecoRepository enderecoRepository, ModelMapper modelMapper) {
        return args -> {
            if (enderecoRepository.count() == 0) {
                EnderecoCreateDTO enderecoDTO = new EnderecoCreateDTO(1L, "Rua das Flores",123,"Apto 101","Próximo à padaria","Centro","20000-000","Rio de Janeiro","RJ","Brasil");
                Endereco endereco = modelMapper.map(enderecoDTO, Endereco.class);
                enderecoRepository.save(endereco);

                enderecoDTO = new EnderecoCreateDTO(2L, "Avenida Paulista",1578,"Sala 305",null,"Bela Vista","01310-200","São Paulo","SP","Brasil");
                endereco = modelMapper.map(enderecoDTO, Endereco.class);
                enderecoRepository.save(endereco);

                enderecoDTO = new EnderecoCreateDTO(3L, "Rua XV de Novembro",45,null,"Ao lado da farmácia","Centro Histórico","80020-310","Curitiba","PR","Brasil");
                endereco = modelMapper.map(enderecoDTO, Endereco.class);
                enderecoRepository.save(endereco);

                enderecoDTO = new EnderecoCreateDTO(2L, "Rua das Acácias",890,"Casa","Próximo ao parque","Jardim Primavera","13050-000","Campinas","SP","Brasil");
                endereco = modelMapper.map(enderecoDTO, Endereco.class);
                enderecoRepository.save(endereco);

                enderecoDTO = new EnderecoCreateDTO(4L, "Travessa do Comércio",12,"Fundos",null,"Comércio","40015-320","Salvador","BA","Brasil");
                endereco = modelMapper.map(enderecoDTO, Endereco.class);
                enderecoRepository.save(endereco);

            }
        };
    }
    @Bean
    @Order(4)
    CommandLineRunner seedProduto(ProdutoRepository produtoRepository, ModelMapper modelMapper) {
        return args -> {
            if (produtoRepository.count() == 0) {
                ProdutoCreateDTO produtoDTO = new ProdutoCreateDTO("Balança dose certa", "Balança dose certa para café colher cor branca 25gr","https://graoseprosa.com.br/wp-content/uploads/2026/01/balanca_dose_certa_para_cafe_colher_cor_branca.jpeg","ACESSORIO",new BigDecimal("28.00"),3);
                Produto produto = modelMapper.map(produtoDTO, Produto.class);
                produtoRepository.save(produto);

                produtoDTO = new ProdutoCreateDTO("Bule de café", "Bule de café resistente ao calor 600ml de vidro transparente com tampa","https://graoseprosa.com.br/wp-content/uploads/2026/01/WhatsApp-Image-2026-01-20-at-10.34.20.jpeg","ACESSORIO",new BigDecimal("89.00"),1);
                produto = modelMapper.map(produtoDTO, Produto.class);
                produtoRepository.save(produto);

                produtoDTO = new ProdutoCreateDTO("Filtro de papel branco", "Filtro de papel branco V60 02 Barista Café.","https://graoseprosa.com.br/wp-content/uploads/2026/02/WhatsApp-Image-2026-02-03-at-10.11.55.jpeg","ACESSORIO",new BigDecimal("15.00"),25);
                produto = modelMapper.map(produtoDTO, Produto.class);
                produtoRepository.save(produto);

                produtoDTO = new ProdutoCreateDTO("Moedor de Café Elétrico", "Moedor de café elétrico de cerâmica com regulagem interna recarregável.","https://graoseprosa.com.br/wp-content/uploads/2026/02/WhatsApp-Image-2026-02-03-at-09.58.22.jpeg","ACESSORIO",new BigDecimal("98.00"),1);
                produto = modelMapper.map(produtoDTO, Produto.class);
                produtoRepository.save(produto);

                produtoDTO = new ProdutoCreateDTO("Suporte porta filtro", "Suporte porta filtro coador café V60 102 de acrílico tipo hario.","https://graoseprosa.com.br/wp-content/uploads/2026/02/WhatsApp-Image-2026-02-03-at-09.58.23.jpeg","ACESSORIO",new BigDecimal("53.00"),10);
                produto = modelMapper.map(produtoDTO, Produto.class);
                produtoRepository.save(produto);

                produtoDTO = new ProdutoCreateDTO("Café Arábica Variedade Catuaí 144", "Café 100% arábica, da variedade Catuaí 144, produzido na Chapada Diamantina – Bahia, a 1.350 metros de altitude.\n" +
                        "Cultivado em região de montanha, esse café se destaca pelo sabor equilibrado, aroma agradável e doçura natural.\n" +
                        "Possui acidez suave, corpo médio e uma bebida macia, fácil de beber, muito aromática e com notas de Mel, Melaço, Licoroso e Floral.\n" +
                        "Um café especial que agrada tanto quem está começando no mundo dos cafés especiais quanto quem já aprecia qualidade.","https://graoseprosa.com.br/wp-content/uploads/2025/12/Captura-de-tela-de-2026-01-28-11-41-38.png","CAFE",new BigDecimal("48.00"),10);
                produto = modelMapper.map(produtoDTO, Produto.class);
                produtoRepository.save(produto);

                produtoDTO = new ProdutoCreateDTO("Café Arábica Variedade Catucai 2SL", "Café 100% arábica, da variedade Catucaí 2SL, cultivado na região do Caparaó, terroir reconhecido pela excelência em cafés de montanha.\n" +
                        "Apresenta perfil sensorial equilibrado, com doçura natural, acidez delicada e corpo médio, resultando em uma bebida elegante, aromática e de finalização limpa. Perfil sensorial com notas de chocolate Amargo, Melaço, Caramelo e Baunilha, aroma intenso e finalização prolongada.\n" +
                        "Um café especial que expressa a identidade e a tradição do Caparaó, ideal para quem valoriza qualidade e origem.","https://graoseprosa.com.br/wp-content/uploads/2026/01/Captura-de-tela-de-2026-01-28-11-35-54.png","CAFE",new BigDecimal("48.00"),10);
                produto = modelMapper.map(produtoDTO, Produto.class);
                produtoRepository.save(produto);

                produtoDTO = new ProdutoCreateDTO("Café Gourmet", "Originário de Varre-Sai (RJ), este café arábica da variedade Catuaí é cultivado a 700 metros de altitude, em um terroir marcado por clima equilibrado e solos que favorecem a expressão sensorial do grão.\n" +
                        "\n" +
                        "A bebida apresenta acidez média, doçura bem integrada e corpo médio a alto, com perfil sensorial elegante. Destacam-se notas cítricas delicadas, nuances de caramelo, além de finalização seca e limpa, revelando um café preciso e bem definido.\n" +
                        "\n" +
                        "Indicado para apreciadores que valorizam cafés de origem, com identidade regional e excelente desempenho em métodos filtrados.","https://graoseprosa.com.br/wp-content/uploads/2026/02/Captura-de-tela-de-2026-02-03-11-15-56.png","CAFE",new BigDecimal("38.00"),10);
                produto = modelMapper.map(produtoDTO, Produto.class);
                produtoRepository.save(produto);

                produtoDTO = new ProdutoCreateDTO("Canephora Variedade Robusta", "Café da espécie Canephora, cultivado no Norte do Espírito Santo, região referência nacional na produção de cafés robustas de alta qualidade.\n" +
                        "Apresenta perfil sensorial intenso, com corpo elevado, amargor equilibrado e baixo teor de acidez, resultando em uma bebida encorpada, marcante e persistente.\n" +
                        "Destaca-se por seu alto teor de doçura após a torra correta, possui aroma marcante e notas de chocolate 70%, aveã, caramelo e excelente cremosidade.\n" +
                        "Um café de personalidade forte, ideal para quem aprecia uma bebida intensa, estruturada e de alto rendimento.","https://graoseprosa.com.br/wp-content/uploads/2026/01/Captura-de-tela-de-2026-01-28-11-44-46.png","CAFE",new BigDecimal("48.00"),10);
                produto = modelMapper.map(produtoDTO, Produto.class);
                produtoRepository.save(produto);

            }
        };
    }
    @Bean
    @Order(5)
    CommandLineRunner seedCupom(CupomRepository cupomRepository, ModelMapper modelMapper) {
        return args -> {
            if (cupomRepository.count() == 0) {
                CupomCreateDTO cupomDTO = new CupomCreateDTO("DESCONTO10", new BigDecimal("10.00"),"FIXO",enumCupom.ATIVO);
                Cupom cupom = modelMapper.map(cupomDTO, Cupom.class);
                cupomRepository.save(cupom);

                cupomDTO = new CupomCreateDTO("PROMO20", new BigDecimal("20.00"),"PERCENTUAL",enumCupom.ATIVO);
                cupom = modelMapper.map(cupomDTO, Cupom.class);
                cupomRepository.save(cupom);

                cupomDTO = new CupomCreateDTO("BLACK50", new BigDecimal("50.00"),"PERCENTUAL",enumCupom.EXPIRADO);
                cupom = modelMapper.map(cupomDTO, Cupom.class);
                cupomRepository.save(cupom);

                cupomDTO = new CupomCreateDTO("WELCOME5", new BigDecimal("1.00"),"FIXO",enumCupom.APLICADO);
                cupom = modelMapper.map(cupomDTO, Cupom.class);
                cupomRepository.save(cupom);

                cupomDTO = new CupomCreateDTO("CAFEBOM", new BigDecimal("15.00"),"FIXO",enumCupom.RESGATADO);
                cupom = modelMapper.map(cupomDTO, Cupom.class);
                cupomRepository.save(cupom);

            }
        };
    }
}
