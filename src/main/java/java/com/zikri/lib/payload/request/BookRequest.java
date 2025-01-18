package java.com.zikri.lib.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String judul;
    private String pengarang;
    private String penerbit;
    private String tahunTerbit;
    private String isbn;
}
