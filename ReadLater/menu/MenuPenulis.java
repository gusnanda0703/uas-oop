package menu;

import java.util.Optional;

import models.Menu;
import models.Penulis;
import repositories.UserRepository;
import utils.ScreenHelper;

public class MenuPenulis extends Menu<Penulis> {
    UserRepository userRepository;

    public MenuPenulis(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|                DATA PENULIS                 |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Tampil Penulis                          |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Tambah Penulis                          |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Edit Penulis                            |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Hapus Penulis                           |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 0 | Kembali                                 |");
            System.out.println("+=============================================+");
            System.out.print("\nSilakan masukan pilihan anda (0...4) : ");
            pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan) {
                case 1 -> tampilData();
                case 2 -> tambah();
                case 3 -> edit();
                case 4 -> hapus();
                case 0 -> {
                    System.out.println("+=============================================+");
                    System.out.println("|            KEMBALI KE MENU UTAMA            |");
                    System.out.println("+=============================================+\n");
                }
                default -> {
                    System.out.println("Pilihan yang anda input tidak tersedia, silakan ulangi kembali.");
                    input.next();
                }
            }
        } while (pilihan != 0);
    }

    @Override
    public void tambah() {
        ScreenHelper.clearConsole();
        System.out.println("+=============================================+");
        System.out.println("|              TAMBAH DATA PENULIS            |");
        System.out.println("+=============================================+");

        System.out.print("Nama Penulis : ");
        var name = input.nextLine();
        System.out.print("Email        : ");
        var email = input.nextLine();
        System.out.print("Password     : ");
        var password = input.nextLine();

        userRepository.add(new Penulis(name, email, password));
        System.out.println("Data penulis berhasil ditambahkan.");
        input.nextLine();
    }

    @Override
    public Optional<Penulis> pilih() {
        return this.pilih(true);
    }

    @Override
    public Optional<Penulis> pilih(boolean required) {
        var listPenulis = userRepository.getPenulis();
        Optional<Penulis> penulisDipilih = Optional.empty();
        if (listPenulis.size() > 0) {
            System.out.printf("\nPenulis yang terdafar %d:\n", listPenulis.size());
            do {
                for (var penulis : listPenulis) {
                    System.out.printf("%d) %s\n", penulis.getId(), penulis.getName());
                }
                System.out.print("Silakan pilih id penulis : ");
                var idPenulis = input.nextInt();
                penulisDipilih = listPenulis.stream().filter(penulis -> penulis.getId() == idPenulis).findFirst();
            } while (required && penulisDipilih.isEmpty());
        } else {
            System.out.println("Data penulis kosong, silakan tambahkan data.");
        }
        input.nextLine();
        return penulisDipilih;
    }

    @Override
    public void tampilData() {
        var listPenulis = userRepository.getPenulis();
        if (listPenulis.size() > 0) {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|              TAMPIL DATA PENULIS            |");
            System.out.println("+=============================================+");
            for (var tempPenulis : listPenulis) {
                System.out.println("Nama Penulis    : " + tempPenulis.getName());
                System.out.println("Email           : " + tempPenulis.getEmail());
                System.out.println("+=============================================+");
            }
        } else {
            System.out.println("Penulis kosong.");
        }
        input.nextLine();
    }

    @Override
    public void edit() {
        var penulisDipilih = pilih();
        if (penulisDipilih.isPresent()) {
            ScreenHelper.clearConsole();

            System.out.println("+=============================================+");
            System.out.println("|              EDIT DATA PENULIS              |");
            System.out.println("+=============================================+");

            System.out.println("Biarkan kosong jika tidak ingin mengubah data.");
            var penulis = penulisDipilih.get();
            System.out.printf("Nama Penulis (%s) : ", penulis.getName());
            var name = input.nextLine();
            System.out.printf("Email        (%s) : ", penulis.getEmail());
            var email = input.nextLine();

            penulis.setName(name.isBlank() ? penulis.getName() : name);
            penulis.setEmail(email.isBlank() ? penulis.getEmail() : email);
            System.out.println("Data penulis berhasil diubah.");
            input.nextLine();
        }
    }

    @Override
    public void hapus() {
        var penulisDipilih = pilih();
        if (penulisDipilih.isPresent()) {
            var penulis = penulisDipilih.get();
            userRepository.remove(penulis);
            System.out.println("Data penulis berhasil dihapus.");
            input.nextLine();
        }
    }
}
