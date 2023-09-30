package project.demo.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor//无参构造方法
@AllArgsConstructor//包含所有参数的构造方法
public class Book {
    /*Spring Boot使用Jackson将实体类转换为json对象，
    json属性名获取方式为get方法，默认的get方法会将属性名读取为小写
    如getName会读取到属性名name*/
    private String ISBN;
    private String name;
    private String type;
    private String press;
    private Integer num;

    //使用JsonProperty自定义json获取的属性名
    //如果使用LomBok插件，get方法为getIsbn,对应获取的属性名为isbn
    @JsonProperty("ISBN")
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        String str = "Book{";
        if(ISBN!=null) str+="ISBN='"+ISBN+ '\'';
        if(name!=null) str+=" name='"+name+ '\'';
        if(type!=null) str+=" type='"+type+ '\'';
        if(press!=null) str+=" press='"+press+ '\'';
        if(num!=null) str+=" num='"+num+ '\'';
        str+="}";
        return str;
    }
}
