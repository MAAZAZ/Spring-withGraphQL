package com.example.ecomproject;

import com.example.ecomproject.models.CategoryModel;
import com.example.ecomproject.models.ProductModel;
import com.example.ecomproject.repositories.CategoryRepository;
import com.example.ecomproject.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class EcomProjectApplication {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        SpringApplication.run(EcomProjectApplication.class, args);
    }

    @Bean
    protected CommandLineRunner commandLineRunner(final ProductRepository productRepository,
                                                  final CategoryRepository categoryRepository)
    {
        return args -> {
            List.of("Audio",
                    "Communications",
                    "Computers",
                    "GPS Navigation Systems",
                    "Marine Electronics",
                    "Networking",
                    "Video",
                    "Radar Detectors",
                    "Video Game Console Accessories",
                    "Video Game Consoles")
                    .forEach(s -> categoryRepository.save(CategoryModel.builder().name(s).build()));

            categoryRepository.findAll().forEach(category -> {
                for(int i=0; i< 5; i++) {
                    final ProductModel product = ProductModel.builder()
                            .name("Electronic" + i)
                            .price(100 + RANDOM.nextDouble(5000))
                            .currency("EUR")
                            .quantity(RANDOM.nextInt(250))
                            .category(category)
                            .build();
                    productRepository.save(product);
                }
            });
        };
    }
}
