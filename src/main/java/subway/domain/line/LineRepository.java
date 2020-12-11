package subway.domain.line;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import subway.view.OutputView;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        validateAddition(line);
        lines.add(line);
    }

    public static boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    private static void validateAddition(Line line) {
        validateNoDuplicate(line);
    }

    private static void validateNoDuplicate(Line line) {
        if(isDuplicate(line)) {
            throw new IllegalArgumentException(OutputView.ERROR_DUPLICATE_NAME);
        }
    }

    private static boolean isDuplicate(Line line) {
        return lines.stream()
                .map(Line::getName)
                .anyMatch(x -> x.equals(line.getName()));
    }
}