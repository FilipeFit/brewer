package com.insightsoftware.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.insightsoftware.brewer.dto.FotoDTO;
import com.insightsoftware.brewer.storage.FotoStorage;
import com.insightsoftware.brewer.storage.FotosStorageRunnable;

@RestController
@RequestMapping("/fotos")
public class FotosController {

  private final FotoStorage fotoStorage;

  @Autowired
  public FotosController(FotoStorage fotoStorage) {
    this.fotoStorage = fotoStorage;
  }

  @PostMapping
  public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
    DeferredResult<FotoDTO> resultado = new DeferredResult<>();

    Thread thread = new Thread(new FotosStorageRunnable(files, resultado, fotoStorage));
    thread.start();

    return resultado;

  }

  @GetMapping("/temp/{nome:.*}")
  public byte[] recuperarFotoTemporaria(@PathVariable String nome) {
    return fotoStorage.recuperarFotoTemporaria(nome);
  }

  @GetMapping("/{nome:.*}")
  public byte[] recuperar(@PathVariable String nome) {
    return fotoStorage.recuperar(nome);
  }

}
