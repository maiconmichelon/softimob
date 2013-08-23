package br.com.michelon.softimob.tela.popup.notifier;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

public enum NotificationType {
    ERROR(ImageRepository.ERROR_32.getImage()),
//    DELETE(ImageCache.getImage("delete.png")),
    WARN(ImageRepository.WARN_32.getImage()),
//    SUCCESS(ImageCache.getImage("ok.png")),
    INFO(ImageRepository.INFO_32.getImage()),
//    LIBRARY(ImageCache.getImage("library.png")),
//    HINT(ImageCache.getImage("hint.png")),
//    PRINTED(ImageCache.getImage("printer.png")),
//    CONNECTION_TERMINATED(ImageCache.getImage("terminated.png")),
//    CONNECTION_FAILED(ImageCache.getImage("connecting.png")),
//    CONNECTED(ImageCache.getImage("connected.png")),
//    DISCONNECTED(ImageCache.getImage("disconnected.png")),
//    TRANSACTION_OK(ImageCache.getImage("ok.png")),
//    TRANSACTION_FAIL(ImageCache.getImage("error.png"))
    ;
    private Image _image;

    private NotificationType(Image img) {
        _image = img;
    }

    public Image getImage() {
        return _image;
    }
}
