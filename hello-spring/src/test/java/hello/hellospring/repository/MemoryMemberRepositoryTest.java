package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {
	MemberRepository repository = new MemoryMemberRepository();
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		//System.out.println("result = "+(result == member));
		Assertions.assertThat(member).isEqualTo(result);
	}
	
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring2").get();
		
		assertThat(result).isEqualTo(member2);
	}
	
	
	@Test
	 public void findAll() {
		//given
		Member member1 = new Member();
	 	member1.setName("spring1");
	 	repository.save(member1);
	 	Member member2 = new Member();
	 	member2.setName("spring2");
	 	repository.save(member2);
	 	//when
	 	List<Member> result = repository.findAll();
	 	//then
	 	assertThat(result.size()).isEqualTo(2);
	 }
}
