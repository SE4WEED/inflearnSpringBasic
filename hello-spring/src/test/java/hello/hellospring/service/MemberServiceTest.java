package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("spring");
		
		//when
		Long saveId = memberService.join(member);
		
		//then		
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	 public void 중복_회원_예외() throws Exception {
	 //Given
		Member member1 = new Member();
	 	member1.setName("spring");
	 	Member member2 = new Member();
	 	member2.setName("spring");
	 //When
	 	memberService.join(member1);
	 	IllegalStateException e = 
	 			assertThrows(IllegalStateException.class,
	 			() -> memberService.join(member2));//예외가 발생해야 한다.
	 	assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
	 }
	
	@Test
	void testFindMembers() {
	}

	@Test
	void testFindOne() {
	}

}
