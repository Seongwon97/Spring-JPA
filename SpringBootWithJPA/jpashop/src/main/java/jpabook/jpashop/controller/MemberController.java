package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        // @Valid를 붙여줘야지 MemberForm에서 설정한 @NotEmpty와 같은 validation기능을 사용할 수 있다.
        // validate만 하게 된다면 오류가 발생하였을 때 오류 화면과 함께 튕기게 된다.
        // 하지만 validate한 것 다음에 BindingResult가 있으면 오류가 result에 담긴뒤 코드가 실행이 된다.

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }
    // 해당 코드에서는 Model에 엔티티를 넘겼지만 api를 만들 때는 절대로 엔티티를 외부로 반환하면 안된다. (템플릿 엔진을 쓸때는 괜찮음)
}
