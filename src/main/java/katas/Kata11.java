package katas;

import com.google.common.collect.ImmutableMap;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
    Goal: Create a datastructure from the given data:

    This time we have 4 seperate arrays each containing lists, videos, boxarts, and bookmarks respectively.
    Each object has a parent id, indicating its parent.
    We want to build an array of list objects, each with a name and a videos array.
    The videos array will contain the video's id, title, bookmark time, and smallest boxart url.
    In other words we want to build the following structure:

    [
        {
            "name": "New Releases",
            "videos": [
                {
                    "id": 65432445,
                    "title": "The Chamber",
                    "time": 32432,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/TheChamber130.jpg"
                },
                {
                    "id": 675465,
                    "title": "Fracture",
                    "time": 3534543,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/Fracture120.jpg"
                }
            ]
        },
        {
            "name": "Thrillers",
            "videos": [
                {
                    "id": 70111470,
                    "title": "Die Hard",
                    "time": 645243,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/DieHard150.jpg"
                },
                {
                    "id": 654356453,
                    "title": "Bad Boys",
                    "time": 984934,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/BadBoys140.jpg"
                }
            ]
        }
    ]

    DataSource: DataUtil.getLists(), DataUtil.getVideos(), DataUtil.getBoxArts(), DataUtil.getBookmarkList()
    Output: the given datastructure
*/
public class Kata11 {
    public static List<Map> execute() {
        List<Map> lists = DataUtil.getLists();
        List<Map> videos = DataUtil.getVideos();
        List<Map> boxArts = DataUtil.getBoxArts();
        List<Map> bookmarkList = DataUtil.getBookmarkList();

        return lists.stream()
                .map(list -> ImmutableMap.of("name", list.get("name"),
                                                "videos", getVideosInfo((Integer) list.get("id"), videos, boxArts, bookmarkList)))
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<Map> getVideosInfo(Integer listId, List<Map> videos, List<Map> boxArts, List<Map> bookmarkList) {
        return videos.stream()
                .filter(video -> video.get("listId").equals(listId))
                .map(video -> ImmutableMap.of("id", video.get("id"),
                                                        "title", video.get("title"),
                                                        "time", getBookmarkTime((Integer) video.get("id"), bookmarkList),
                                                        "boxart", getSmallestBoxArtUrl((Integer) video.get("id"), boxArts)))
                .collect(Collectors.toUnmodifiableList());
    }
    private static Integer getBookmarkTime(Integer videoId, List<Map> bookmarkList) {
        return Integer.valueOf(bookmarkList.stream()
                .filter(bookmark -> bookmark.get("videoId").equals(videoId))
                .findFirst()
                .map(bookmark -> bookmark.get("time"))
                .orElseThrow(() -> new NullPointerException("There is no bookmark time assigned"))
                .toString());
    }

    private static String getSmallestBoxArtUrl(Integer videoId, List<Map> boxArts) {
        return boxArts.stream()
                .filter(boxArt -> boxArt.get("videoId").equals(videoId))
                .reduce(Kata11::getSmallestBoxart)
                .map(boxArt -> boxArt.get("url"))
                .orElseThrow(() -> new NullPointerException("There is no box art url assigned"))
                .toString();
    }

    private static Map getSmallestBoxart(Map boxArt1, Map boxArt2) {
        return (Integer) boxArt1.get("width") <= (Integer) boxArt2.get("width")
                && (Integer) boxArt1.get("height") <= (Integer) boxArt2.get("height") ? boxArt1 : boxArt2;
    }
}
