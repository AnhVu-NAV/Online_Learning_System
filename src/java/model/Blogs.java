package model;

import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mocun
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blogs {

    private int blogId;
    private int authorId;
    private String title;
    private String content;
    private String thumbnailUrl;
    private String briefInfo;
    private String category;
    private Date createdDate;
    private Date updatedDate;
    private String authorName;

    // toString method
    @Override
    public String toString() {
        return "Blogs{"
                + "blogId=" + blogId
                + ", authorId=" + authorId
                + ", title='" + title + '\''
                + ", content='" + content + '\''
                + ", thumbnailUrl='" + thumbnailUrl + '\''
                + ", briefInfo='" + briefInfo + '\''
                + ", category='" + category + '\''
                + ", createdDate=" + createdDate
                + ", updatedDate=" + updatedDate
                + ", authorName=" + authorName
                + '}';
    }
}
