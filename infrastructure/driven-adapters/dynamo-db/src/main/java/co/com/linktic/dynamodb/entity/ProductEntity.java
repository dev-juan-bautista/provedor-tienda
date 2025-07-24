package co.com.linktic.dynamodb.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class ProductEntity {

    private String id;

    private String name;

    private Double price;

    private String description;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }

    @DynamoDbAttribute("nombre")
    public String getName() {
        return name;
    }

    @DynamoDbAttribute("precio")
    public Double getPrice() {
        return price;
    }

    @DynamoDbAttribute("descripccion")
    public String getDescription() {
        return description;
    }
}
