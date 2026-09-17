package br.fiap.aguiabranca_backend;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class AguiabrancaBackendApplication {

    @Bean
    public MongoClient mongoClient() {
        // Força a conexão direta com o MongoDB Atlas na nuvem, ignorando o localhost
        ConnectionString connectionString = new ConnectionString("mongodb+srv://joaopdr924_db_user:aguia1234@cluster0.d91qif4.mongodb.net/aguiabranca_db?retryWrites=true&w=majority&appName=Cluster0");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "aguiabranca_db");
    }

    public static void main(String[] args) {
        SpringApplication.run(AguiabrancaBackendApplication.class, args);
    }
}