package com.roque.app.vista;

import com.roque.app.modelo.GeneradorXML;
import com.roque.app.util.Base64Util;
import com.roque.app.util.ZipUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FormularioAlta extends JFrame {

    private static final String CODIGO_ESTABLECIMIENTO = "0000004063";
    private static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter ISO_DATE_TIME_OFFSET = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
    private static final Pattern SOLO_LETRAS = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$");
    private static final Pattern EMAIL_VALIDO = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private static final Pattern DNI_VALIDO = Pattern.compile("^\\d{7}[A-Z]$");
    private static final Pattern NIE_VALIDO = Pattern.compile("^[A-Z]\\d{6}[A-Z]$");

    private final FondoPanel fondo = new FondoPanel();
    private final JPanel panelFormulario = new JPanel();
    private final JPanel panelPersonas = new JPanel();

    private final JTextField txtReferencia = new JTextField(15);
    private final JSpinner spnFechaEntrada = new JSpinner(new SpinnerDateModel());
    private final JSpinner spnFechaSalida = new JSpinner(new SpinnerDateModel());
    private final JTextField txtNumPersonas = new JTextField(5);
    private final JTextField txtNumHabitaciones = new JTextField(5);
    private final JComboBox<String> cmbInternet = new JComboBox<>(new String[]{"false", "true"});
    private final JComboBox<String> cmbTipoPago = new JComboBox<>(new String[]{"EFECT", "CARD", "BIZUM"});
    private final JButton btnAgregarPersona = new JButton("Añadir persona");
    private final JButton btnGenerar = new JButton("Generar Alta");

    private final List<PersonaPanel> personas = new ArrayList<>();

    public FormularioAlta() {
        configurarVentana();
        construirFormulario();
        aplicarEstilos();
        agregarPersona();
    }

    private void configurarVentana() {
        setTitle("Reserva de Hotel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(fondo);
        fondo.setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(1000, 760));
        setLocationRelativeTo(null);
    }

    private void construirFormulario() {
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1, true),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        JLabel titulo = new JLabel("RESERVA DE HOTEL", SwingConstants.CENTER);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panelFormulario.add(titulo);
        panelFormulario.add(Box.createVerticalStrut(12));

        spnFechaEntrada.setEditor(new JSpinner.DateEditor(spnFechaEntrada, "yyyy-MM-dd"));
        spnFechaSalida.setEditor(new JSpinner.DateEditor(spnFechaSalida, "yyyy-MM-dd"));

        panelFormulario.add(crearFila("Referencia", txtReferencia));
        panelFormulario.add(crearFila("Fecha entrada", spnFechaEntrada));
        panelFormulario.add(crearFila("Fecha salida", spnFechaSalida));
        panelFormulario.add(crearFila("Num. personas", txtNumPersonas));
        panelFormulario.add(crearFila("Num. habitaciones", txtNumHabitaciones));
        panelFormulario.add(crearFila("Internet", cmbInternet));
        panelFormulario.add(crearFila("Tipo pago", cmbTipoPago));

        panelFormulario.add(Box.createVerticalStrut(10));
        JLabel etiquetaPersonas = new JLabel("Personas");
        etiquetaPersonas.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelFormulario.add(etiquetaPersonas);
        panelFormulario.add(Box.createVerticalStrut(8));

        panelPersonas.setLayout(new BoxLayout(panelPersonas, BoxLayout.Y_AXIS));
        panelPersonas.setOpaque(false);
        panelFormulario.add(panelPersonas);

        JPanel accionesPersona = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        accionesPersona.setOpaque(false);
        accionesPersona.add(btnAgregarPersona);
        panelFormulario.add(Box.createVerticalStrut(6));
        panelFormulario.add(accionesPersona);

        JPanel accionesFinal = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 8));
        accionesFinal.setOpaque(false);
        accionesFinal.add(btnGenerar);
        panelFormulario.add(Box.createVerticalStrut(12));
        panelFormulario.add(accionesFinal);

        JScrollPane scroll = new JScrollPane(panelFormulario);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setPreferredSize(new Dimension(820, 640));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        fondo.add(scroll, gbc);

        limitarNumerico(txtNumPersonas, 3);
        limitarNumerico(txtNumHabitaciones, 3);

        btnAgregarPersona.addActionListener(e -> agregarPersona());
        btnGenerar.addActionListener(e -> generarAlta());
    }

    private JPanel crearFila(String label, Component input) {
        JPanel fila = new JPanel(new BorderLayout(12, 0));
        fila.setOpaque(false);
        fila.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(250, 28));
        fila.add(lbl, BorderLayout.WEST);
        fila.add(input, BorderLayout.CENTER);
        return fila;
    }

    private void agregarPersona() {
        PersonaPanel persona = new PersonaPanel(personas.size() + 1);
        personas.add(persona);
        panelPersonas.add(persona);
        panelPersonas.add(Box.createVerticalStrut(8));
        txtNumPersonas.setText(String.valueOf(personas.size()));
        panelPersonas.revalidate();
        panelPersonas.repaint();
    }

    private void generarAlta() {
        try {
            validarCabecera();
            for (PersonaPanel persona : personas) {
                persona.validar();
            }

            String xml = construirXml();
            GeneradorXML.generarXML(xml);
            ZipUtil.comprimirXML();
            String base64 = Base64Util.convertirZipBase64();
            Base64Util.guardarTXT(base64);

            JOptionPane.showMessageDialog(this, "Comunicación generada correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void validarCabecera() {
        if (txtNumHabitaciones.getText().isBlank()) {
            throw new IllegalArgumentException("Num. habitaciones obligatorio");
        }
        getLocalDate(spnFechaEntrada);
        getLocalDate(spnFechaSalida);
    }

    private LocalDate getLocalDate(JSpinner spinner) {
        Date value = (Date) spinner.getValue();
        return Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private String construirXml() {
        LocalDate hoy = LocalDate.now();
        Random random = new Random();

        LocalDate entrada = getLocalDate(spnFechaEntrada);
        LocalDate salida = getLocalDate(spnFechaSalida);

        OffsetDateTime fechaEntradaAleatoria = entrada
                .atTime(LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(60)))
                .atOffset(ZoneOffset.ofHours(2));
        OffsetDateTime fechaSalidaAleatoria = salida
                .atTime(LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(60)))
                .atOffset(ZoneOffset.ofHours(2));

        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<alt:peticion xmlns:alt=\"http://www.neg.hospedajes.mir.es/altaParteHospedaje\">\n");
        xml.append("  <solicitud>\n");
        xml.append("    <codigoEstablecimiento>").append(CODIGO_ESTABLECIMIENTO).append("</codigoEstablecimiento>\n");
        xml.append("    <comunicacion>\n");
        xml.append("      <contrato>\n");
        xml.append("        <referencia>").append(escapeXml(txtReferencia.getText())).append("</referencia>\n");
        xml.append("        <fechaContrato>").append(hoy.format(ISO_DATE)).append("</fechaContrato>\n");
        xml.append("        <fechaEntrada>").append(fechaEntradaAleatoria.format(ISO_DATE_TIME_OFFSET)).append("</fechaEntrada>\n");
        xml.append("        <fechaSalida>").append(fechaSalidaAleatoria.format(ISO_DATE_TIME_OFFSET)).append("</fechaSalida>\n");
        xml.append("        <numPersonas>").append(txtNumPersonas.getText()).append("</numPersonas>\n");
        xml.append("        <numHabitaciones>").append(txtNumHabitaciones.getText()).append("</numHabitaciones>\n");
        xml.append("        <internet>").append(cmbInternet.getSelectedItem()).append("</internet>\n");
        xml.append("        <pago>\n");
        xml.append("          <tipoPago>").append(cmbTipoPago.getSelectedItem()).append("</tipoPago>\n");
        xml.append("          <fechaPago>").append(hoy.format(ISO_DATE)).append("</fechaPago>\n");
        xml.append("        </pago>\n");
        xml.append("      </contrato>\n");

        for (PersonaPanel persona : personas) {
            xml.append(persona.aXml());
        }

        xml.append("    </comunicacion>\n");
        xml.append("  </solicitud>\n");
        xml.append("</alt:peticion>\n");
        return xml.toString();
    }

    private static String escapeXml(String value) {
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    private void limitarNumerico(JTextField field, int maxLength) {
        ((AbstractDocument) field.getDocument()).setDocumentFilter(new RegexFilter("\\d*", maxLength));
    }

    private static class RegexFilter extends DocumentFilter {
        private final Pattern pattern;
        private final int maxLength;

        RegexFilter(String regex, int maxLength) {
            this.pattern = Pattern.compile(regex);
            this.maxLength = maxLength;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            String current = fb.getDocument().getText(0, fb.getDocument().getLength());
            String next = current.substring(0, offset) + (text == null ? "" : text) + current.substring(offset + length);
            if (next.length() <= maxLength && pattern.matcher(next).matches()) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            replace(fb, offset, 0, string, attr);
        }
    }

    private class PersonaPanel extends JPanel {

        private final JComboBox<String> cmbRol = new JComboBox<>(new String[]{"VIP", "TURISTA"});
        private final JTextField txtNombre = new JTextField();
        private final JTextField txtApellido1 = new JTextField();
        private final JTextField txtApellido2 = new JTextField();
        private final JComboBox<String> cmbTipoDocumento = new JComboBox<>(new String[]{"DNI", "NIE"});
        private final JTextField txtNumeroDocumento = new JTextField();
        private final JSpinner fechaNacimiento = new JSpinner(new SpinnerDateModel());
        private final JTextField txtNacionalidad = new JTextField("ESP");
        private final JComboBox<String> cmbSexo = new JComboBox<>(new String[]{"H", "M"});
        private final JTextField txtDireccion = new JTextField();
        private final JTextField txtDireccionComplementaria = new JTextField();
        private final JTextField txtCodigoMunicipio = new JTextField();
        private final JTextField txtCodigoPostal = new JTextField();
        private final JTextField txtTelefono = new JTextField();
        private final JTextField txtCorreo = new JTextField();

        PersonaPanel(int index) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setOpaque(true);
            setBackground(new Color(255, 255, 255, 170));
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 255, 255, 140), 1, true),
                    BorderFactory.createEmptyBorder(12, 12, 12, 12)
            ));

            JLabel tituloPersona = new JLabel("Persona " + index);
            tituloPersona.setFont(new Font("Segoe UI", Font.BOLD, 15));
            add(tituloPersona);
            add(Box.createVerticalStrut(6));

            fechaNacimiento.setEditor(new JSpinner.DateEditor(fechaNacimiento, "yyyy-MM-dd"));
            txtDireccionComplementaria.setToolTipText("Ejemplo: Portal - Piso - Letra");

            limitarNumerico(txtCodigoMunicipio, 5);
            limitarNumerico(txtCodigoPostal, 5);
            limitarNumerico(txtTelefono, 9);

            add(crearFila("Rol", cmbRol));
            add(crearFila("Nombre", txtNombre));
            add(crearFila("Apellido 1", txtApellido1));
            add(crearFila("Apellido 2", txtApellido2));
            add(crearFila("Tipo documento", cmbTipoDocumento));
            add(crearFila("Número documento", txtNumeroDocumento));
            add(crearFila("Fecha nacimiento", fechaNacimiento));
            add(crearFila("Nacionalidad", txtNacionalidad));
            add(crearFila("Sexo", cmbSexo));
            add(crearFila("Dirección", txtDireccion));
            add(crearFila("Dirección complementaria", txtDireccionComplementaria));
            add(crearFila("Código municipio", txtCodigoMunicipio));
            add(crearFila("Código postal", txtCodigoPostal));
            add(crearFila("Teléfono", txtTelefono));
            add(crearFila("Correo", txtCorreo));
        }

        void validar() {
            if (!SOLO_LETRAS.matcher(txtNacionalidad.getText()).matches()) {
                throw new IllegalArgumentException("Nacionalidad solo puede tener letras");
            }
            if (!EMAIL_VALIDO.matcher(txtCorreo.getText()).matches()) {
                throw new IllegalArgumentException("Correo inválido");
            }

            String tipo = (String) cmbTipoDocumento.getSelectedItem();
            String doc = txtNumeroDocumento.getText();
            if (("DNI".equals(tipo) && !DNI_VALIDO.matcher(doc).matches())
                    || ("NIE".equals(tipo) && !NIE_VALIDO.matcher(doc).matches())) {
                throw new IllegalArgumentException("Número de documento inválido para " + tipo);
            }
        }

        String aXml() {
            LocalDate fechaNac = getLocalDate(fechaNacimiento);

            StringBuilder sb = new StringBuilder();
            sb.append("      <persona>\n");
            sb.append("        <rol>").append(cmbRol.getSelectedItem()).append("</rol>\n");
            sb.append("        <nombre>").append(escapeXml(txtNombre.getText())).append("</nombre>\n");
            sb.append("        <apellido1>").append(escapeXml(txtApellido1.getText())).append("</apellido1>\n");
            sb.append("        <apellido2>").append(escapeXml(txtApellido2.getText())).append("</apellido2>\n");
            sb.append("        <tipoDocumento>").append(cmbTipoDocumento.getSelectedItem()).append("</tipoDocumento>\n");
            sb.append("        <numeroDocumento>").append(txtNumeroDocumento.getText()).append("</numeroDocumento>\n");
            sb.append("        <fechaNacimiento>").append(fechaNac.format(ISO_DATE)).append("</fechaNacimiento>\n");
            sb.append("        <nacionalidad>").append(escapeXml(txtNacionalidad.getText())).append("</nacionalidad>\n");
            sb.append("        <sexo>").append(cmbSexo.getSelectedItem()).append("</sexo>\n");
            sb.append("        <direccion>\n");
            sb.append("          <direccion>").append(escapeXml(txtDireccion.getText())).append("</direccion>\n");
            sb.append("          <direccionComplementaria>").append(escapeXml(txtDireccionComplementaria.getText())).append("</direccionComplementaria>\n");
            sb.append("          <codigoMunicipio>").append(txtCodigoMunicipio.getText()).append("</codigoMunicipio>\n");
            sb.append("          <codigoPostal>").append(txtCodigoPostal.getText()).append("</codigoPostal>\n");
            sb.append("          <pais>ESP</pais>\n");
            sb.append("        </direccion>\n");
            sb.append("        <telefono>").append(txtTelefono.getText()).append("</telefono>\n");
            sb.append("        <correo>").append(escapeXml(txtCorreo.getText())).append("</correo>\n");
            sb.append("      </persona>\n");
            return sb.toString();
        }
    }

    private void aplicarEstilos() {
        panelFormulario.setOpaque(true);
        panelFormulario.setBackground(new Color(255, 255, 255, 150));
        btnGenerar.setBackground(new Color(198, 156, 109));
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFocusPainted(false);

        btnAgregarPersona.setBackground(new Color(80, 140, 180));
        btnAgregarPersona.setForeground(Color.WHITE);
        btnAgregarPersona.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioAlta().setVisible(true));
    }
}
