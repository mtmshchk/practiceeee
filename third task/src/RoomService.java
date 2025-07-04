interface RoomService<T> {

    void clean(T room);
    void reserve(T room);
    void free(T room);
}