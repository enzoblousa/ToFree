package com.inflatable.freelancer.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.inflatable.freelancer.model.User;
import com.inflatable.freelancer.model.Role;
import com.inflatable.freelancer.repository.UserRepository;
import com.inflatable.freelancer.model.Inflatable;
import com.inflatable.freelancer.repository.InflatableRepository;

@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, 
                                  InflatableRepository inflatableRepository,
                                  PasswordEncoder passwordEncoder) {
        return args -> {
            // Criar usuário admin padrão se não existir
            if (userRepository.findByEmail("admin@email.com").isEmpty()) {
                User admin = new User();
                admin.setName("Administrador");
                admin.setEmail("admin@email.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Usuário admin criado: admin@email.com / admin123");
            }

            // Criar usuário freelancer padrão se não existir
            if (userRepository.findByEmail("freelancer@email.com").isEmpty()) {
                User freelancer = new User();
                freelancer.setName("João Freelancer");
                freelancer.setEmail("freelancer@email.com");
                freelancer.setPassword(passwordEncoder.encode("freelancer123"));
                freelancer.setRole(Role.FREELANCER);
                userRepository.save(freelancer);
                System.out.println("Usuário freelancer criado: freelancer@email.com / freelancer123");
            }

            // Criar usuário cliente padrão se não existir
            if (userRepository.findByEmail("cliente@email.com").isEmpty()) {
                User client = new User();
                client.setName("Maria Cliente");
                client.setEmail("cliente@email.com");
                client.setPassword(passwordEncoder.encode("cliente123"));
                client.setRole(Role.CLIENT);
                userRepository.save(client);
                System.out.println("Usuário cliente criado: cliente@email.com / cliente123");
            }

            // Criar brinquedos iniciais se não existirem
            if (inflatableRepository.count() == 0) {
                Inflatable castelo = new Inflatable();
                castelo.setName("Castelo Inflável Grande");
                castelo.setDescription("Castelo inflável para festas infantis");
                castelo.setPricePerDay(java.math.BigDecimal.valueOf(150.00));
                castelo.setSize("3x3m");
                castelo.setAvailable(true);
                castelo.setCreatedBy(1L);
                inflatableRepository.save(castelo);

                Inflatable toboga = new Inflatable();
                toboga.setName("Tobogã Aquático");
                toboga.setDescription("Tobogã inflável para piscina");
                toboga.setPricePerDay(java.math.BigDecimal.valueOf(200.00));
                toboga.setSize("4x2m");
                toboga.setAvailable(true);
                toboga.setCreatedBy(1L);
                inflatableRepository.save(toboga);

                Inflatable piscina = new Inflatable();
                piscina.setName("Piscina de Bolinhas");
                piscina.setDescription("Piscina inflável com bolinhas");
                piscina.setPricePerDay(java.math.BigDecimal.valueOf(100.00));
                piscina.setSize("2x2m");
                piscina.setAvailable(true);
                piscina.setCreatedBy(1L);
                inflatableRepository.save(piscina);

                System.out.println("Brinquedos infláveis iniciais criados");
            }
        };
    }
}