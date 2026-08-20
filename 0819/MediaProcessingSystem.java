public class MediaProcessingSystem {

    static abstract class MediaFile {
        protected String fileName;

        public MediaFile(String fileName) {
            this.fileName = fileName;
        }

        public abstract void showInfo();
    }

    interface Playable {
        void play();
    }

    interface Compressible {
        void compress();
    }

    static class ImageFile extends MediaFile implements Compressible {
        public ImageFile(String fileName) {
            super(fileName);
        }

        @Override
        public void showInfo() {
            System.out.println("圖片：" + fileName);
        }

        @Override
        public void compress() {
            System.out.println(fileName + " 圖片壓縮完成");
        }
    }

    static class AudioFile extends MediaFile
            implements Playable, Compressible {

        public AudioFile(String fileName) {
            super(fileName);
        }

        @Override
        public void showInfo() {
            System.out.println("音訊：" + fileName);
        }

        @Override
        public void play() {
            System.out.println(fileName + " 音訊播放中");
        }

        @Override
        public void compress() {
            System.out.println(fileName + " 音訊壓縮完成");
        }
    }

    static class VideoFile extends MediaFile
            implements Playable, Compressible {

        public VideoFile(String fileName) {
            super(fileName);
        }

        @Override
        public void showInfo() {
            System.out.println("影片：" + fileName);
        }

        @Override
        public void play() {
            System.out.println(fileName + " 影片播放中");
        }

        @Override
        public void compress() {
            System.out.println(fileName + " 影片壓縮完成");
        }
    }

    public static void main(String[] args) {
        MediaFile[] files = {
            new ImageFile("photo.jpg"),
            new AudioFile("music.mp3"),
            new VideoFile("movie.mp4")
        };

        for (MediaFile file : files) {
            file.showInfo();

            if (file instanceof Playable playable) {
                playable.play();
            }

            if (file instanceof Compressible compressible) {
                compressible.compress();
            }

            System.out.println();
        }
    }
}
