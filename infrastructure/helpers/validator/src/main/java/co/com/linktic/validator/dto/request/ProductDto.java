package co.com.linktic.validator.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class ProductDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("nombre")
    @NotNull(message = "El campo nombre es obligatorio")
    @Valid
    private String name;

    @JsonProperty("precio")
    @NotNull(message = "El campo precio es obligatorio")
    @Valid
    private Double price;

    @JsonProperty("descripccion")
    private String description;

}
