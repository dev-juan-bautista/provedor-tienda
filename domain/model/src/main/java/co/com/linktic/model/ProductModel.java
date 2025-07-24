package co.com.linktic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {

    private String id;

    private String name;

    private Double price;

    private String description;

}
