package byrnes.jonathan.eqprototype.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class EditQuestionDto {

    @NotBlank(message = "You must enter a question.")
    private String questionStr;

    @NotNull(message = "You must enter a time limit.")
    private int timeLimit;

    @NotNull(message = "You must enter this questions worth.")
    private int worth;

    @NotEmpty(message = "You must enter an answer.")
    private List<String> answers;


    private List<String> options;
}
