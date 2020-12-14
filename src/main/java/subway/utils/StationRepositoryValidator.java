package subway.utils;

import subway.domain.line.LineRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.view.ErrorView;

public class StationRepositoryValidator {


    public static void validateAddition(Station station) {
        validateNoDuplicate(station);
    }

    public static void validateDeletion(String name) {
        validateNoLineConnection(name);
    }

    private static boolean isDuplicate(Station station) {
        return StationRepository.stations().contains(station);
    }

    private static void validateNoDuplicate(Station station) {
        if(isDuplicate(station)) {
            throw new IllegalArgumentException(ErrorView.NAME_DUPLICATE);
        }
    }

    private static void validateNoLineConnection(String name) {
        if(LineRepository.hasLineWithStation(name)) {
            throw new IllegalArgumentException(ErrorView.STATION_DELETE_FAIL);
        }
    }
}
