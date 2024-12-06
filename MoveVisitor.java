public interface MoveVisitor {

    void visit(Label label, int dx, int dy);

    void visit(Line line, int dx, int dy);

    void visit(Triangle triangle, int dx, int dy);
}