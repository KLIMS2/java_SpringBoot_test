package com.ysj.springboot_test.home.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController
{
  private int num;
  private List<Person> people;

  public HomeController()
  {
    num = 0;
    people = new ArrayList<>();
  }

  @GetMapping("/home")
  public String home()
  {
    return "index";
  }

  @GetMapping("/home/main")
  @ResponseBody
  public String f()
  {
    return """
        <a href="/home/main2">main2</a>
        """;
  }

  @GetMapping("/home/main2")
  @ResponseBody
  public String f2()
  {
    return "안녕하세요.";
  }

  @GetMapping("/home/main3")
  @ResponseBody
  public String f3()
  {
    return "스프링부트는 재밌습니다.";
  }

  @GetMapping("/home/increase")
  @ResponseBody
  public int increase()
  {
    return ++num;
  }

  @GetMapping("/home/decrease")
  @ResponseBody
  public int decrease()
  {
    return --num;
  }

  @GetMapping("/home/init")
  @ResponseBody
  public int init()
  {
    num = 0;
    return num;
  }

  @GetMapping("/home/plus")
  @ResponseBody
  public int plus(int a, int b)
  {
    return a + b;
  }

  @GetMapping("/home/plus2")
  @ResponseBody
  public int plus2(@RequestParam(defaultValue = "1") int a, int b)
  {
    return a + b;
  }

  @GetMapping("/home/plus3/{_a}/{_b}")
  @ResponseBody
  public int plus3(@PathVariable int _a, @PathVariable int _b)
  {
    return _a + _b;
  }

  @GetMapping("/home/showMember")
  @ResponseBody
  public _Member showMember()
  {
    _Member member = new _Member(1, "user1", "asdf");
    return member;
  }

  @GetMapping("/home/addPerson")
  @ResponseBody
  public String addPerson(String name, int age)
  {
    Person person = new Person(name, age);
    people.add(person);

    return "%d번 사람이 추가되었습니다.".formatted(person.getId());
  }

  @GetMapping("/home/showPeople")
  @ResponseBody
  public List<Person> showPeople()
  {
    return people;
  }

  @GetMapping("/home/removePerson")
  @ResponseBody
  public String removePerson(@RequestParam(defaultValue = "-1") int id)
  {
    if(id == -1)
    {
      return "id를 입력해주세요.";
    }

    Person person = null;

    for(Person person1 : people)
    {
      if(person1.getId() == id)
      {
        person = person1;
        break;
      }
    }

    if(person == null)
    {
      return "%d번 사람이 존재하지 않습니다.".formatted(id);
    }

    people.remove(person);
    return "%d번 사람이 삭제되었습니다.".formatted(id);
  }

  @GetMapping("/home/modifyPerson")
  @ResponseBody
  public String modifyPerson(int id, String name, int age)
  {
    Person person = null;

    for(Person person1 : people)
    {
      if(person1.getId() == id)
      {
        person = person1;
        break;
      }
    }

    if(person == null)
    {
      return "%d번 사람이 존재하지 않습니다.".formatted(id);
    }

    person.setName(name);
    person.setAge(age);
    return "%d번 사람이 수정되었습니다.".formatted(id);
  }

  @GetMapping("/home/cookie/increase")
  @ResponseBody
  public int cookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException
  {
    int cookieCount = 0;

    if(req.getCookies() != null)
    {
      for(Cookie cookie : req.getCookies())
      {
        if(cookie.getName().equals("count"))
        {
          cookieCount = Integer.parseInt(cookie.getValue());
          break;
        }
      }
    }

    int newCookieCount = cookieCount + 1;
    resp.addCookie(new Cookie("count", newCookieCount + ""));
    return newCookieCount;
  }

  @GetMapping("/home/showHtml")
  public String showHtml()
  {
    return "member/login";
  }
}

@Data
@AllArgsConstructor
class _Member
{
  private int id;
  private String loginId;
  private String name;
}

@Data
@AllArgsConstructor
class Person
{
  private static int lastId;
  private int id;
  private String name;
  private int age;

  static
  {
    lastId = 0;
  }

  public Person(String name, int age)
  {
    this(++lastId, name, age);
  }
}