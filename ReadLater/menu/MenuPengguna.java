package menu;

import java.util.Optional;

import models.Menu;
import models.Pengguna;
import repositories.UserRepository;
import utils.ScreenHelper;

public class MenuPengguna extends Menu<Pengguna> {
    UserRepository userRepository;

    public MenuPengguna(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void tampilMenu() {
        int pilihan;
        do {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|                DATA PENGGUNA                 |");
            System.out.println("+=============================================+");
            System.out.println("| 1 | Tampil Pengguna                          |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 2 | Tambah Pengguna                          |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 3 | Edit Pengguna                            |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| 4 | Hapus Pengguna                           |");
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
        System.out.println("|              TAMBAH DATA PENGGUNA            |");
        System.out.println("+=============================================+");

        System.out.print("Nama Pengguna : ");
        var name = input.nextLine();
        System.out.print("Email        : ");
        var email = input.nextLine();
        System.out.print("Password     : ");
        var password = input.nextLine();

        userRepository.add(new Pengguna(name, email, password));
        System.out.println("Data pengguna berhasil ditambahkan.");
        input.nextLine();
    }

    @Override
    public Optional<Pengguna> pilih() {
        return this.pilih(true);
    }

    @Override
    public Optional<Pengguna> pilih(boolean required) {
        var listPengguna = userRepository.getPenggunas();
        Optional<Pengguna> penggunaDipilih = Optional.empty();
        if (listPengguna.size() > 0) {
            System.out.printf("\nPengguna yang terdafar %d:\n", listPengguna.size());
            do {
                for (var pengguna : listPengguna) {
                    System.out.printf("%d) %s\n", pengguna.getId(), pengguna.getName());
                }
                System.out.print("Silakan pilih id pengguna : ");
                var idPengguna = input.nextInt();
                penggunaDipilih = listPengguna.stream().filter(pengguna -> pengguna.getId() == idPengguna).findFirst();
            } while (required && penggunaDipilih.isEmpty());
        } else {
            System.out.println("Data pengguna kosong, silakan tambahkan data.");
        }
        input.nextLine();
        return penggunaDipilih;
    }

    @Override
    public void tampilData() {
        var listPengguna = userRepository.getPenggunas();
        if (listPengguna.size() > 0) {
            ScreenHelper.clearConsole();
            System.out.println("+=============================================+");
            System.out.println("|              TAMPIL DATA PENGGUNA            |");
            System.out.println("+=============================================+");
            for (var pengguna : listPengguna) {
                System.out.println("Nama Pengguna    : " + pengguna.getName());
                System.out.println("Email           : " + pengguna.getEmail());
                System.out.println("+=============================================+");
            }
        } else {
            System.out.println("Pengguna kosong.");
        }
        input.nextLine();
    }

    @Override
    public void edit() {
        var penggunaDipilih = pilih();
        if (penggunaDipilih.isPresent()) {
            ScreenHelper.clearConsole();

            System.out.println("+=============================================+");
            System.out.println("|              EDIT DATA PENGGUNA              |");
            System.out.println("+=============================================+");

            System.out.println("Biarkan kosong jika tidak ingin mengubah data.");
            var pengguna = penggunaDipilih.get();
            System.out.printf("Nama Pengguna (%s) : ", pengguna.getName());
            var name = input.nextLine();
            System.out.printf("Email        (%s) : ", pengguna.getEmail());
            var email = input.nextLine();

            pengguna.setName(name.isBlank() ? pengguna.getName() : name);
            pengguna.setEmail(email.isBlank() ? pengguna.getEmail() : email);
            System.out.println("Data pengguna berhasil diubah.");
            input.nextLine();
        }
    }

    @Override
    public void hapus() {
        var penggunaDipilih = pilih();
        if (penggunaDipilih.isPresent()) {
            var pengguna = penggunaDipilih.get();
            userRepository.remove(pengguna);
            System.out.println("Data pengguna berhasil dihapus.");
            input.nextLine();
        }
    }
}
