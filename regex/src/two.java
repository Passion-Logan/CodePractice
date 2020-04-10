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
     * 例如，表达式[^c]ar 匹配一个后面跟着ar的除了c的任意字符 匹配 par 和 gar。
     *          "[^c]ar" => The car parked in the garage.
     *
     * 3 重复次数 后面跟着元字符 +，* or ? 的，用来指定匹配子模式的次数。 这些元字符在不同的情况下有着不同的意思。
     *
     * 3.1 * 号   *号匹配 在*之前的字符出现大于等于0次。 例如，表达式 a* 匹配0或更多个以a开头的字符。
     * 表达式[a-z]* 匹配一个行中所有以小写字母开头的字符串。 匹配 The car parked in the garage
     *          "[a-z]*" => The car parked in the garage #21.
     *
     *  *字符和.字符搭配可以匹配所有的字符.*。 *和表示匹配空格的符号\s连起来用，
     *  如表达式\s*cat\s*匹配0或更多个空格开头和0或更多个空格结尾的cat字符串。 匹配 cat
     *          "\s*cat\s*" => The fat cat sat on the concatenation.
     *
     * 3.2 + 号   +号匹配+号之前的字符出现 >=1 次。
     * 例如表达式c.+t 匹配以首字母c开头以t结尾，中间跟着至少一个字符的字符串 匹配 cat sat on the mat
     *          "c.+t" => The fat cat sat on the mat.
     *
     * 3.3 ? 号  在正则表达式中元字符 ? 标记在符号前面的字符为可选，即出现 0 或 1 次。
     * 例如，表达式 [T]?he 匹配字符串 he 和 The
     *          "[T]?he" => The car is parked in the garage.
     *
     */
}
