package chapter2.index;

public class News {
    private int id; // id
    private String title; // 标题
    private String content; // 内容
    private int reply; // 评论数
    
    public News() {
    }
    
    public News(int id, String title, String content, int reply) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
        this.reply = reply;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }
}
