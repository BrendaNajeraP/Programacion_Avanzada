namespace Tarea02
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            SuspendLayout();
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Name = "Form1";
            Text = "Form1";
            Load += Form1_Load;
            ResumeLayout(false);
        }

        #endregion


        public class Ejem3 : Form
        {
            private static Label la, lb;
            private static Button Bboton;

            public Ejem3()
            {
                this.Text = "Ejemplo 3";
                this.StartPosition = FormStartPosition.CenterScreen;
                this.ClientSize = new System.Drawing.Size(300, 300);

                // Configuración del Label la
                la = new Label();
                la.Text = "El triángulo base 5 y altura 2";
                la.SetBounds(10, 10, 200, 30);

                // Configuración del Label lb
                lb = new Label();
                lb.SetBounds(10, 100, 200, 30);

                // Configuración del Button Bboton
                Bboton = new Button();
                Bboton.Text = "Calcular";
                Bboton.SetBounds(10, 50, 100, 30);

                // Evento del botón
                Bboton.Click += (sender, e) =>
                {
                    lb.Text = "El área del triángulo es 5";
                };

                // Agregar controles al formulario
                this.Controls.Add(la);
                this.Controls.Add(lb);
                this.Controls.Add(Bboton);
            }

            [STAThread]
            public static void Main()
            {
                Application.EnableVisualStyles();
                Application.Run(new Ejem3());
            }
        }
   




