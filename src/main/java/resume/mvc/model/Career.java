package resume.mvc.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Career {
    private String name;
    private String email;
    private String address;
    private String phone;
    private String birthday;
}
