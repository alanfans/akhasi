import com.luosl.akhasi.domain.User;
import com.luosl.akhasi.domain.base.DaoContext;
import com.luosl.akhasi.domain.base.DataTable;
import com.luosl.akhasi.lucence.Analyzers;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/6/3.
 * by alanfansccc
 */
public class Test {
    public static void main(String[] args) {
        Analyzers.genAnalyzer();
    }
}
