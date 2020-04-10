/**
 * ClassName: two
 *
 * @author WQL
 * @Description:
 * @date: 2020/4/10 23:52
 * @since JDK 1.8
 */
public class two {

    /**
     * 元字符讲解：
     * 1 点运算符 .是元字符中最简单的例子。 .匹配任意单个字符，但不匹配换行符。 例如，表达式.ar匹配一个任意字符后面跟着是a和r的字符串。
     *          ".ar" => The car parked in the garage.   匹配 car par gar
     *
     * 2 字符集 [] 字符集也叫做字符类。 方括号用来指定一个字符集。 在方括号中使用连字符来指定字符集的范围。
     * 在方括号中的字符集不关心顺序。 例如，表达式[Tt]he 匹配 the 和 The。
     *          "[Tt]he" => The car parked in the garage.
     *   方括号的句号就表示句号。 表达式 ar[.] 匹配 ar.字符串
     *          "ar[.]" => A garage is a good place to park a car.
     *
     * 2.1 否定字符集 一般来说 ^ 表示一个字符串的开头，但它用在一个方括号的开头的时候，它表示这个字符集是否定的。
     * 例如，表达式[^c]ar 匹配一个后面跟着ar的除了c的任意字符
     *          "[^c]ar" => The car parked in the garage.
     *
     */
}
