package controller;

import java.util.List;

import dao.MemberDAO;
import model.Member;

public class MemberController {

    private MemberDAO memberDAO;

    public MemberController() {

        memberDAO = new MemberDAO();

    }

    public boolean addMember(Member member) {

        return memberDAO.addMember(member);

    }
    
    public List<Member> getAllMembers() {

        return memberDAO.getAllMembers();

    }
    
    public boolean deleteMember(int memberId) {

        return memberDAO.deleteMember(memberId);

    }
    
    public boolean updateMember(Member member) {

        return memberDAO.updateMember(member);

    }
    
    public Member getMemberById(int memberId) {

        return memberDAO.getMemberById(memberId);

    }
    
    public List<Member> searchMembers(String keyword){

        return memberDAO.searchMembers(keyword);

    }
}