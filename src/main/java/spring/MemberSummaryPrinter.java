package spring;

public class MemberSummaryPrinter extends MemberPrinter {
    @Override
    public void print(Member member) {
        System.out.println("summary");
    }
}
