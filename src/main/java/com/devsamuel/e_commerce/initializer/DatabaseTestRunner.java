package com.devsamuel.e_commerce.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DatabaseTestRunner implements CommandLineRunner{

        @Autowired
        private DataSource dataSource;

        @Override
        public void run(String... args) throws Exception {
            try {
                System.out.println("Tentando conectar ao banco de dados...");
                Connection connection = dataSource.getConnection();
                if (connection != null) {
                    System.out.println("Conexão bem-sucedida!");
                } else {
                    System.out.println("Falha na conexão!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            }
        }
    }

