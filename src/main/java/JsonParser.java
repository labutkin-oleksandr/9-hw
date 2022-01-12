class JsonParser {
    private User user;

    public JsonParser(User user) {
        this.user = user;
    }

    public String fromJson() {
        return "{\n" +
                "  \"name\": \"" + user.getName() + "\",\n" +
                "  \"age\": " + user.getAge() + "\n" +
                "}";
    }
}
