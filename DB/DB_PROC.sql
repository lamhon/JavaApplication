USE JavaApp
GO

CREATE PROC sp_UpdatePassword
@ID BIGINT,
@Pwd VARCHAR(128)
AS
BEGIN
	DECLARE @res BIT

	UPDATE tb_USER
	SET Password = @Pwd
	WHERE ID = @ID
		SET @res = 1
	SELECT @res
END

CREATE PROC sp_UpdateInfoUSER
@ID BIGINT,
@Name NVARCHAR(128),
@Phone VARCHAR(20),
@Address NVARCHAR(MAX),
@Email NVARCHAR(MAX)
AS
BEGIN
	DECLARE @res BIT

	UPDATE tb_USER
	SET Name = @Name, Phone = @Phone, Address = @Address, Email = @Email
	WHERE ID = @ID
		SET @res = 1
	SELECT @res
END

CREATE PROC sp_UpdateInfoUser_Admin
@ID BIGINT,
@Name NVARCHAR(128),
@Phone VARCHAR(20),
@Address NVARCHAR(MAX),
@Email NVARCHAR(MAX),
@RoleID BIGINT,
@STT BIT
AS
BEGIN
	DECLARE @res BIT

	UPDATE tb_USER
	SET Name = @Name, Phone = @Phone, Address = @Address, Email = @Email, RoleID = @RoleID, STT = @STT
	WHERE ID = @ID
		SET @res = 1
	SELECT @res
END

CREATE PROC sp_UpdateInfoSuplier
@ID BIGINT,
@Name NVARCHAR(MAX),
@Address NVARCHAR(MAX),
@Phone VARCHAR(20),
@Email VARCHAR(128),
@STT BIT
AS
BEGIN
	DECLARE @res BIT

	UPDATE tb_SUPLIER
	SET SuplierName = @Name, Address = @Address, Phone = @Phone, Email = @Email, STT = @STT
	WHERE ID = @ID
		SET @res = 1
	SELECT @res
END

CREATE PROC sp_UpdateInfoProduct
@ID BIGINT,
@Name NVARCHAR(MAX),
@Price DECIMAL(19,4),
@SL INT,
@Suplier BIGINT,
@STT BIT
AS
BEGIN
	DECLARE @res BIT

	UPDATE tb_PRODUCT
	SET ProductName = @Name, Price = @Price, SL = @SL, Suplier = @Suplier, STT = @STT
	WHERE ID = @ID
		SET @res = 1
	SELECT @res
END

CREATE PROC sp_InsertBill
@UserCreate BIGINT
AS
BEGIN
	DECLARE @dt VARCHAR(50)
	DECLARE @res BIGINT
	SET @dt = concat(CONVERT(VARCHAR(10), getdate(), 111), ' ', convert(time, getdate()))

	INSERT INTO tb_BILL(UserCreate, DateCreate) VALUES (@UserCreate, @dt)

	SELECT @res = ID FROM tb_BILL WHERE UserCreate = @UserCreate AND DateCreate = @dt
	SELECT @res 
END


CREATE PROC sp_UpdateRoleFunction
@IDRole BIGINT,
@IDFunction VARCHAR(128),
@STT BIT
AS
BEGIN
	DECLARE @res BIT

	UPDATE tb_ROLEFUNCTION
	SET STT = @STT
	WHERE IDRole = @IDRole AND IDFunction = @IDFunction
	SET @res = 1
	SELECT @res
END

CREATE PROC sp_InsertRoleFunc
@IDRole BIGINT
AS
BEGIN
	DECLARE @res BIT

	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_create', 1)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_meReport', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_meRole', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_product', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_productCreate', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_productRepair', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_staff', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_staffChangepass', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_staffCreate', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_staffRepair', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_suplier', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_suplierCreate', 0)
	INSERT INTO tb_ROLEFUNCTION (IDRole, IDFunction, STT) VALUES (@IDRole, 'btn_suplierRepair', 0)

	SET @res = 1
	SELECT @res

END

CREATE PROC sp_UpdateRole
@IDRole BIGINT,
@NewName NVARCHAR(128),
@STT BIT
AS
BEGIN
	DECLARE @res BIT

	UPDATE tb_ROLE
	SET STT = @STT, RoleName = @NewName
	WHERE ID = @IDRole
	SET @res = 1
	SELECT @res
END
