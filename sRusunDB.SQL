DROP TABLE IF EXISTS Logs;
DROP TABLE IF EXISTS Unit;
DROP TABLE IF EXISTS IOT;
DROP TABLE IF EXISTS Saluran_Air;
DROP TABLE IF EXISTS Pengguna;
GO

--PENGGUNA
CREATE TABLE Pengguna(
   NIK INT PRIMARY KEY,
    No_Ponsel VARCHAR(15),
    Alamat_Domisili VARCHAR(100),
    OTPGenerate_OTP INT,
    realOTP AS ((OTPGenerate_OTP + LEN(No_Ponsel)) % 10000),
    TipeUser INT
-- 0 Pemilik, 1 Administrator, 2 Pengelola
)


CREATE TABLE IOT(
Liter_Per_Menit Int,
ID_Pemakaian_air Int PRIMARY KEY,
No_Serial Int,
Akumulasi Int,
Utililtas_Air Int,
NIK_Pengelola INT,

FOREIGN KEY (NIK_Pengelola) REFERENCES Pengguna(NIK),
--Utilitas Air Derived ke harian, Bulan, Tahun

)

--Sarusun
CREATE TABLE Unit (
    Tower CHAR(1), 
    Lantai INT,
    Nomor_Unit INT,
    ID_Pemakaian_air INT,
    NIK_Pengguna INT,
    -- computed column sebagai kombinasi unik
    NomorUnitSarusun AS (
        CAST(Tower AS VARCHAR) + 
        CAST(Lantai AS VARCHAR) + 
        RIGHT('0000' + CAST(Nomor_Unit % 10000 AS VARCHAR), 4)
    ) PERSISTED,

    PRIMARY KEY (NomorUnitSarusun),

    FOREIGN KEY (ID_Pemakaian_air) REFERENCES IOT(ID_Pemakaian_air),
	FOREIGN KEY (NIK_Pengguna) REFERENCES Pengguna(NIK)
)




--LOG
CREATE TABLE Logs(
ID_log INT PRIMARY KEY,
Aksi INT, -- 0 TUTUP, 1 BUKA SALURAN AIR
Waktu DATETIME,
NIK_Pengguna INT,--sebagai fk untuk pengguna
CONSTRAINT FK_Log_Pengguna FOREIGN KEY (NIK_Pengguna) REFERENCES Pengguna(NIK)
)

INSERT INTO Pengguna (NIK, No_Ponsel, Alamat_Domisili, OTPGenerate_OTP, TipeUser) VALUES
(101, '081234567890', 'Jl. Anggrek No. 5', 1234, 0),
(102, '082345678901', 'Jl. Mawar No. 2', 5678, 1),
(103, '083456789012', 'Jl. Melati No. 8', 4321, 2);


INSERT INTO IOT (Liter_Per_Menit, ID_Pemakaian_air, No_Serial, Akumulasi, Utililtas_Air, NIK_Pengelola) VALUES
(15, 301, 1001, 500, 1, 103),
(20, 302, 1002, 800, 2, 103);

INSERT INTO Unit (Tower, Lantai, Nomor_Unit, ID_Pemakaian_air, NIK_Pengguna) VALUES
('A', 3, 101, 301, 101),
('B', 5, 205, 302, 102);

INSERT INTO Logs (ID_log, Aksi, Waktu, NIK_Pengguna) VALUES
(1, 1, '2025-05-21 08:01:00', 101), -- Pemilik membuka saluran
(2, 0, '2025-05-21 10:05:00', 103), -- Pengelola menutup saluran
(3, 1, '2025-05-21 14:15:00', 102), -- Administrator membuka saluran
(4, 0, '2025-05-21 16:45:00', 101), -- Pemilik menutup saluran
(5, 1, '2025-05-22 07:50:00', 103); -- Pengelola membuka lagi

SELECT *
FROM lOGS;

SELECT *
FROM Pengguna;


SELECT * FROM IOT;

SELECT * FROM UNIT;